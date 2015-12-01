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
PUPPET_MODULES="abrt afs apache:upstream_150 bridged cernlib cinder cloud_common cloud_monitoring concat filemapper firewall flume glance haproxy horizon inifile kerberos keystone lemon limits logrotate magnum memcached motd mysql network neutron:1748-neutrondev nova openstack_clients:osclients-testingrepo openstacklib osrepos psacct puppet puppetdbquery sssd stdlib sudo swap_file sysctl teigi:tbag_teigiurl xinetd"

# PUPPET_HOSTGROUPS holds the list of hostgroups we need to run the build(s)
PUPPET_HOSTGROUPS="cloud_adm:qa cloud_blockstorage cloud_compute:selinux cloud_container cloud_dashboard cloud_identity cloud_image cloud_networking:1718-neutronsetup"

# OS_PODS holds the list of pods to be started on 'launch'
OS_PODS=${OS_PODS:-keystone glance cinder neutron nova compute client horizon}

# docker registry to push container images to (see push)
DOCKER_REGISTRY=${DOCKER_REGISTRY:-docker.cern.ch/cloud-infrastructure}

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
		exit_on_err $?
		ln -s it-puppet-module-$module/code $module
		if [[ ! -z $branch ]]; then
			cd it-puppet-module-$module
			exit_on_err $?
			git checkout $branch
			exit_on_err $?
			cd -
		fi
		branch=''
	done
	for hg in $PUPPET_HOSTGROUPS;
	do
		cd $CLOUDDEV_PUPPET
		IFS=':' read -r module branch <<< "$hg"
		git clone -q http://git.cern.ch/cernpub/it-puppet-hostgroup-$module
		exit_on_err $?
		ln -s it-puppet-hostgroup-$module/code hg_$module
		if [[ ! -z $branch ]]; then
			cd it-puppet-hostgroup-$module
			exit_on_err $?
			git checkout $branch
			exit_on_err $?
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
	wget --quiet https://github.com/kubernetes/kubernetes/archive/v1.1.2.tar.gz
	tar zxf v1.1.2.tar.gz
	mv kubernetes-1.1.2/* .
	rm -rf kubernetes-1.1.2 v1.1.2.tar.gz
	#patch -s -p0 $CLOUDDEV_KUB/hack/local-up-cluster.sh < $CLOUDDEV/kubernetes/local-cluster.patch
	exit_on_err $?
}

# start the kubernetes cluster
kubernetes_start() {
	cluster_cleanup
	# make sure the ebtables module is loaded
	sudo modprobe ebtables
	# start the kube daemons
	cd $CLOUDDEV_KUB
	make > /tmp/kubernetes-build.log 2>&1
	sudo PATH=$PATH GOROOT=$GOROOT GOPATH=$GOPATH ETCD=$ETCD ALLOW_PRIVILEGED="true" KUBELET_ARGS="--cluster-dns 10.0.0.10 --cluster-domain cluster.local" ./hack/local-up-cluster.sh > /tmp/kubernetes-local.log 2>&1 &
	echo 'waiting for kubernetes start (and build if not done before)...'
	while ! kubectl get pod > /dev/null 2>&1
	do
		sleep 5
	done
	exit_on_err $?
	sudo chown -R $USER $CLOUDDEV_KUB
	exit_on_err $?
}

# start the base cluster pods
cluster_pod_base_start() {
	echo "starting the base pods (skydns, puppet, ceph)"
	# launch the pods
	cd $CLOUDDEV/kubernetes
	for z in skydns-rc.yaml skydns-svc.yaml puppet-pod.yaml puppet-svc.yaml ceph-pod.yaml wigner-pod.yaml; do
		kubectl create -f $z
	done

	kubectl get pod | grep Pending > /dev/null 2>&1
	echo "waiting for pods to be ready..."
	while [ $? -eq 0 ]
	do
		sleep 2
		kubectl get pod | grep Pending > /dev/null 2>&1
	done

	for cluster in ceph wigner; do
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 auth add client.images -i /etc/ceph/${cluster}.client.images.keyring
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 auth add client.volumes -i /etc/ceph/${cluster}.client.volumes.keyring
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 osd pool create images 32
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 osd pool create volumes 32
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 osd pool create volumes-critical 32
	done

	echo "waiting for puppetdb to start..."
	while ! kubectl exec -p puppet -c puppetdb -- /usr/bin/curl -s http://localhost:8080/v3/facts > /dev/null 2>&1
	do
		sleep 2
	done
	exit_on_err $?
}

# start with a clean runtime
cluster_cleanup() {
	echo "cleaning up any kubernetes, etcd and docker leftovers..."
	sudo killall etcd > /dev/null 2>&1
	for k in $(ps aux | grep kube | awk '{print $2}'); do sudo kill -9 $k; done > /dev/null 2>&1
	sudo docker ps --all | awk '{print $1}' | xargs sudo docker rm -f > /dev/null 2>&1
}

# start the base cluster
cluster_restart() {
	kubernetes_start
	exit_on_err $?
	cluster_pod_base_start
	exit_on_err $?
}

# launch the openstack and controller pods
cluster_pod_launch() {
	# need some regexp magic when launching a specific tag
	if [ ! -z $1 ]; then
		echo "launching pods using tag '$1'..."
		sed "/image:.*mysql/ s/\$/:$1/" $CLOUDDEV/kubernetes/controller-pod.yaml > /tmp/controller-pod.yaml
		for pod in $OS_PODS
		do
			sed -e "s/puppetagent/${pod}:$1/g" $CLOUDDEV/kubernetes/${pod}-pod.yaml > /tmp/${pod}-pod.yaml
		done
	else
		echo "lanching pods using a full puppet run...."
		cp $CLOUDDEV/kubernetes/controller-pod.yaml /tmp
		for pod in $OS_PODS
		do
			cp $CLOUDDEV/kubernetes/${pod}-pod.yaml /tmp
		done
	fi
	# we can now launch the pods
	for pod in controller $OS_PODS
	do
		echo "creating pod ${pod}..."
		kubectl create -f /tmp/${pod}-pod.yaml
		if [ -e $CLOUDDEV/kubernetes/${pod}-svc.yaml ]; then
			kubectl create -f $CLOUDDEV/kubernetes/${pod}-svc.yaml
		fi
	done
	echo "waiting for pods to be ready..."
	while kubectl get pod | grep Pending > /dev/null 2>&1
	do
		sleep 2
	done
	# run puppet in the openstack pods, even if built from tag
	for pod in $OS_PODS
	do
		# run puppet on pod
		sudo docker exec $(sudo docker ps | grep $pod | grep init | awk '{print $1}') /usr/bin/puppet agent -t
		if [[ $? > 2  ]]; then
			exit_on_err 1
		fi
	done
}

# commit the OS docker containers and tag them
cluster_pod_tag() {
	containers="mysql $OS_PODS"
	for container in $containers
	do
		cid=$(sudo docker ps | grep init | grep $container | awk '{print $1}')
		if [ -z $cid ]; then
			cid=$(sudo docker ps | grep $container | awk '{print $1}')
		fi
		dockerimg=$DOCKER_REGISTRY/$container
		echo "committing new image for $container, $dockerimg:$1..."
		sudo docker commit $cid $dockerimg:$1
		exit_on_err $?
		for tag in ${@:2}; do
			echo "adding additional tag '$tag' for container $container"
			sudo docker tag -f $dockerimg:$1 $dockerimg:$tag
			exit_on_err $?
		done
	done
}

# push the given tags to the registry
cluster_pod_push() {
	containers="mysql $OS_PODS"
	for container in $containers
	do
		dockerimg=$DOCKER_REGISTRY/$container
		for tag in ${@}; do
			echo "pushing tag '$tag' for container $container, $dockerimg:$tag..."
			sudo docker push $dockerimg:$tag
			exit_on_err $?
		done
	done
}

# install required centos dependencies
centos_install() {
	echo "installing dependencies for centos..."
	printf "[docker-main-repo]\nname=Docker main Repository\nbaseurl=https://yum.dockerproject.org/repo/main/centos/7\nenabled=1\ngpgcheck=1\ngpgkey=https://yum.dockerproject.org/gpg" > /etc/yum.repos.d/docker.repo
	sed -i '/^Defaults\s*requiretty/d' /etc/sudoers
	sudo yum install -y wget git vim docker-engine etcd golang patch psmisc
	exit_on_err $?
	sed -i "s#^ExecStart.*#ExecStart=/usr/bin/docker daemon --dns 137.138.17.5 --insecure-registry docker.cern.ch --bip 172.17.0.1/16 -H fd://#g" /lib/systemd/system/docker.service
	iptables -F
	# launch docker
	systemctl daemon-reload
	systemctl start docker
}

exit_on_err() {
	if [[ $1 != 0 ]]; then
		exit $1
	fi
}

# run tempest tests against the dev setup
tempest_run() {
	echo "running tempest tests..."
	sudo docker exec $(sudo docker ps | grep client | grep init | awk '{print $1}') /etc/tempest/run.sh
	exit_on_err $?

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
		sudo docker login -u docker -p docker -e 'foo@bar' docker.cern.ch
		;;
	'restart')
		cluster_restart
		;;
	'launch')
		cluster_pod_launch $2
		;;
	'last')
		cluster_pod_launch last
		;;
	'tag')
		cluster_pod_tag "${@:2}"
		;;
	'push')
		cluster_pod_push "${@:2}"
		;;
	'cleanup')
		cluster_cleanup
		;;
	'centos')
		centos_install
		;;
	'tempest')
		tempest_run
		exit $?
		;;
	*)
		echo "Usage: cci-dev COMMAND
Helper to handle a CERN openstack dev workspace.

COMMAND can be one of:
  prepare      Prepare the dev workspace (fetch kubernetes, puppet modules, ...)
  restart      Cleanup any running containers and recreate the base containers (skydns, puppet, ceph)
  launch [tag] Launch the openstack containers, optionally from 'tag' (docker image tag) - otherwise full puppet run
  last         Launch the 'last' built openstack containers - wrapper for 'launch last'
  tag [tag]    Tag all running containers with the give tag (commit first, then tag)
  push [tags]  (done by CI only) Push the current OS containers as a new image, optionally tagging with the given list
  cleanup      Cleanup any running containers so we get a clean set
  centos       Install required dependencies for CentOS
  tempest      Run tempest tests against the dev environment

Required environment settings:
export CLOUDDEV=~/ws/cloud-dev
export CLOUDDEV_PUPPET=~/ws/cern-puppet
export CLOUDDEV_KUB=~/ws/kubernetes
"
		exit 1
		;;
esac

