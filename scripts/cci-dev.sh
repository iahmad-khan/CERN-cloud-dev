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

	kubectl exec -it -p ceph -c cephall -- /usr/bin/ceph --connect-timeout 10 auth add client.images -i /etc/ceph/ceph.client.images.keyring
	kubectl exec -it -p ceph -c cephall -- /usr/bin/ceph --connect-timeout 10 auth add client.volumes -i /etc/ceph/ceph.client.volumes.keyring
	kubectl exec -it -p ceph -c cephall -- /usr/bin/ceph --connect-timeout 10 osd pool create images 64
	kubectl exec -it -p ceph -c cephall -- /usr/bin/ceph --connect-timeout 10 osd pool create volumes 64
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

# launch a specific pod
cluster_pod_start() {
	cd $CLOUDDEV/kubernetes
	kubectl create -f keystone-pod.yaml
	kubectl create -f keystone-svc.yaml
	echo "waiting for keystone pod to be ready..."
	while [ $? -eq 0 ]
	do
		sleep 5
		kubectl get pod keystone | grep Pending > /dev/null 2>&1
	done
	# run puppet on keystone
	sudo docker exec $(sudo docker ps | grep keystone | grep init | awk '{print $1}') /usr/bin/puppet agent -t
	return $?
}

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
		sudo rm /opt/cloud-dev
		sudo ln -s $CLOUDDEV /opt/cloud-dev
		puppet_manifest_checkout
		sudo rm /opt/puppet-modules
		sudo ln -s $CLOUDDEV_PUPPET /opt/puppet-modules
		kubernetes_install
		kubernetes_start
		exit $?
		;;
	'restart')
		cluster_restart
		exit $?
		;;
	'launch')
		cluster_pod_start
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
		echo "Specify one of prepare, restart, launch, cleanup, centos"
		exit 1
		;;
esac

