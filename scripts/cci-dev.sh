#!/bin/bash

if [ -z $CLOUDDEV ]; then
	echo "CLOUDDEV needs to be defined in your environment"
	exit 1
fi

if [ -z $CLOUDDEV_PUPPET ]; then
	echo "CLOUDDEV_PUPPET needs to be defined in your environment"
	exit 1
fi

if [ -z $CLOUDDEV_KUB ]; then
	echo "CLOUDDEV_KUB needs to be defined in your environment"
	exit 1
fi

export PATH=$PATH:$CLOUDDEV_KUB/_output/local/go/bin

# PUPPET_MODULES holds the list of module dependencies that we need to run the build
PUPPET_MODULES="abrt apache:upstream_150 cernlib cinder cloud_common cloud_monitoring concat firewall flume glance inifile kerberos keystone lemon limits logrotate motd mysql openstack_clients osrepos psacct puppet puppetdbquery stdlib teigi:tbag_teigiurl"

# PUPPET_HOSTGROUPS holds the list of hostgroups we need to run the build(s)
PUPPET_HOSTGROUPS="cloud_adm cloud_blockstorage cloud_identity cloud_image"

# OS_PODS holds the list of pods to be started on 'launch'
OS_PODS=${OS_PODS:-keystone glance}

# docker registry to push container images to (see push)
DOCKER_REGISTRY=${DOCKER_REGISTRY:-docker-reg.cern.ch:5000}

# Checkout all the puppet modules and hostgroups
puppet_manifest_checkout() {
	if [ -e $CLOUDDEV_PUPPET ]; then
		echo "$CLOUDDEV_PUPPET exists, not touching..."
		return
	fi
	echo "cloning puppet modules and hostgroups into ${CLOUDDEV_PUPPET}..."
	mkdir -p $CLOUDDEV_PUPPET
	for mod in $PUPPET_MODULES
	do
		cd $CLOUDDEV_PUPPET
		IFS=':' read -r module branch <<< "$mod"
		git clone -q http://git.cern.ch/cernpub/it-puppet-module-$module
		ln -s it-puppet-module-$module/code $module
		if [[ ! -z $branch ]]; then
			cd it-puppet-module-$module
			git checkout $branch
			cd -
		fi
		branch=''
	done
	for hg in $PUPPET_HOSTGROUPS;
	do
		cd $CLOUDDEV_PUPPET
		git clone -q http://git.cern.ch/cernpub/it-puppet-hostgroup-$hg
		ln -s it-puppet-hostgroup-$hg/code hg_$hg
		if [[ ! -z $branch ]]; then
			cd it-puppet-hostgroup-$hg
			git checkout $branch
			cd -
		fi
	done
}

# install kubernetes from a fixed release
kubernetes_install() {
	if [ -e $CLOUDDEV_KUB ]; then
		echo "$CLOUDDEV_KUB exists, not touching..."
		return
	fi
	echo "installing kubernetes at ${CLOUDDEV_KUB}..."
	mkdir -p $CLOUDDEV_KUB
	cd $CLOUDDEV_KUB
	wget --quiet https://github.com/GoogleCloudPlatform/kubernetes/archive/v0.17.1.tar.gz
	tar zxf v0.17.1.tar.gz
	mv kubernetes-0.17.1/* .
	rm -rf kubernetes-0.17.1
	patch -s -p0 $CLOUDDEV_KUB/hack/local-up-cluster.sh < $CLOUDDEV/kubernetes/local-cluster.patch
	return $?
}

# start the kubernetes cluster
kubernetes_start() {
	cluster_cleanup
	# start the kube daemons
	cd $CLOUDDEV_KUB
	sudo PATH=$PATH GOROOT=$GOROOT ./hack/local-up-cluster.sh > /tmp/kubernetes-local.log 2>&1 &
	echo 'waiting for kubernetes start (and build if not done before)...'
	while ! kubectl get pod > /dev/null 2>&1
	do
		sleep 5
	done
	sudo chown -R $USER $CLOUDDEV_KUB
	return $?
}

# start the base cluster pods
cluster_pod_base_start() {
	echo "starting the base pods (skydns, puppet, controller, ceph)"
	# launch the pods
	cd $CLOUDDEV/kubernetes
	for z in dns-hack.json skydns-rc.yaml skydns-svc.yaml puppet-pod.yaml puppet-svc.yaml controller-pod.yaml controller-svc.yaml ceph-pod.yaml; do
		kubectl create -f $z
	done

	kubectl get pod ceph | grep Pending > /dev/null 2>&1
	echo "waiting for pods to be ready..."
	while [ $? -eq 0 ]
	do
		sleep 2
		kubectl get pod | grep Pending > /dev/null 2>&1
	done

	kubectl exec -it -p ceph -c cephall -- HOME=/ /usr/bin/ceph --connect-timeout 10 auth add client.images -i /etc/ceph/ceph.client.images.keyring
	kubectl exec -it -p ceph -c cephall -- HOME=/ /usr/bin/ceph --connect-timeout 10 auth add client.volumes -i /etc/ceph/ceph.client.volumes.keyring
	kubectl exec -it -p ceph -c cephall -- HOME=/ /usr/bin/ceph --connect-timeout 10 osd pool create images 64
	kubectl exec -it -p ceph -c cephall -- HOME=/ /usr/bin/ceph --connect-timeout 10 osd pool create volumes 64

	echo "waiting for puppetdb to start..."
	while ! kubectl exec -p puppet -c puppetdb -- grep 'Finished database' /var/log/puppetdb/puppetdb.log > /dev/null 2>&1
	do
		sleep 2
	done
	return $?
}

# start with a clean runtime
cluster_cleanup() {
	echo "cleaning up any kubernetes or docker leftovers..."
	sudo killall -9 kube-apiserver kube-controller-manager kube-proxy kube-scheduler kubelet etcd > /dev/null 2>&1
	sudo docker ps --all | awk '{print $1}' | xargs sudo docker rm -f > /dev/null 2>&1
	return $?
}

# start the base cluster
cluster_restart() {
	kubernetes_start
	cluster_pod_base_start
	return $?
}

# trigger a full rebuild of all OS pods
cluster_pod_rebuild() {
	cd $CLOUDDEV/kubernetes
	for pod in $OS_PODS
	do
		echo "creating pod ${pod}..."
		kubectl create -f ${pod}-pod.yaml
		kubectl create -f ${pod}-svc.yaml
	done
	for pod in $OS_PODS
	do
		echo "waiting for ${pod} pod to be ready to run puppet..."
		while kubectl get pod $pod | grep Pending > /dev/null 2>&1
		do
			sleep 2
		done
		# run puppet on pod
		sudo docker exec $(sudo docker ps | grep $pod | grep init | awk '{print $1}') /usr/bin/puppet agent -t
		if [[ $? > 2  ]]; then
			return $?
		fi
	done
	return $?
}

# Relaunch all OS pods using the 'latest' image
cluster_pod_latest() {
	cd $CLOUDDEV/kubernetes
	for pod in $OS_PODS
	do
		echo "creating pod ${pod}..."
		sed -e "s/puppetagent:latest/${pod}/g" ${pod}-pod.yaml > /tmp/${pod}-pod.yaml
		kubectl create -f /tmp/${pod}-pod.yaml
		kubectl create -f ${pod}-svc.yaml
	done
	for pod in $OS_PODS
	do
		echo "waiting for ${pod} pod to be ready to run puppet..."
		kubectl get pod ${pod} | grep Pending > /dev/null 2>&1
		while [ $? -eq 0 ]
		do
			kubectl get pod ${pod} | grep Pending > /dev/null 2>&1
		done
		sudo docker exec $(sudo docker ps | grep $pod | grep init | awk '{print $1}') /usr/bin/puppet agent -t
		if [[ $? > 2 ]]; then
			echo "${pod} puppet run failed"
			return $?
		fi
	done
}

# commit the OS docker containers and push them to the registry
cluster_pod_push() {
	for pod in mysql $OS_PODS
	do
		id=$(sudo docker ps | grep init | grep keystone | awk '{print $1}')
		docker_img=$DOCKER_REGISTRY/$pod:latest
		sudo docker commit $id $docker_img
		sudo docker push $docker_img
		if [ $? -ne 0 ]; then
			return $?
		fi
	done
}

# install required centos dependencies
centos_install() {
	echo "installing dependencies for centos..."
	sed -i '/^Defaults\s*requiretty/d' /etc/sudoers
	sudo yum install -y wget git vim docker etcd golang patch psmisc
	sed -i "s/^# INSECURE_REGISTRY.*/INSECURE_REGISTRY='--insecure-registry docker-reg.cern.ch:5000'/g" /etc/sysconfig/docker
	# launch docker (for some reason the systemd init script is failing right now)
	if ! sudo docker ps > /dev/null 2>&1; then
		sudo docker -d --insecure-registry docker-reg.cern.ch:5000 > /tmp/docker.log 2>&1 &
	fi
	return $?
}

case "$1" in
	'prepare')
		sudo rm -f /opt/cloud-dev
		sudo ln -s $CLOUDDEV /opt/cloud-dev
		puppet_manifest_checkout
		sudo rm -f /opt/puppet-modules
		sudo ln -s $CLOUDDEV_PUPPET /opt/puppet-modules
		kubernetes_install
		kubernetes_start
		exit $?
		;;
	'restart')
		cluster_restart
		exit $?
		;;
	'rebuild')
		cluster_pod_rebuild
		exit $?
		;;
	'latest')
		cluster_pod_latest
		exit $?
		;;
	'push')
		cluster_pod_push
		exit $?
		;;
	'cleanup')
		cluster_cleanup
		exit $?
		;;
	'centos')
		centos_install
		exit $?
		;;
	*)
		echo "Usage: cci-dev COMMAND
Helper to handle a CERN openstack dev workspace.

COMMAND can be one of:
  prepare  Prepare the dev workspace (fetch kubernetes, puppet modules, ...)
  restart  Cleanup any running containers and recreate the base containers (skydns, puppet, controller)
  rebuild  Rebuild each of the openstack containers from scratch (full puppet run)
  latest   Launch new openstack containers using the 'latest' image (and run puppet after for update)
  push     (done by CI only) Push the current OS containers as the new 'latest' in the docker registry
  cleanup  Cleanup any running containers so we get a clean set
  centos   Install required dependencies for CentOS

Required environment settings:
export CLOUDDEV=~/ws/cloud-dev
export CLOUDDEV_PUPPET=~/ws/cloud-dev/cern-puppet
export CLOUDDEV_KUB=~/ws/cloud-dev/kubernetes
"
		exit 1
		;;
esac

