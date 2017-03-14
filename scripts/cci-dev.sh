#!/bin/bash

if [ -z $CLOUDDEV ] || [ -z $CLOUDDEV_PUPPET ] || [ -z $CLOUDDEV_KUB ]; then
	echo "
Required environment settings: CLOUDDEV, CLOUDDEV_PUPPET, CLOUDDEV_KUB
Example:
export CLOUDDEV=~/ws/cloud-dev
export CLOUDDEV_PUPPET=~/ws/cern-puppet
export CLOUDDEV_KUB=~/ws/kubernetes
"
	exit 1
fi


export PATH=$PATH:$CLOUDDEV_KUB/_output/bin

KUBERNETES_VERSION=1.6.1

# PUPPET_MODULES holds the list of module dependencies that we need to run the build
PUPPET_MODULES="
abrt
afs
aitools
apache
augeasproviders_core
augeasproviders_sysctl
base
bridged
cernlib
cernpuppet
cinder
cloud_common
cloud_monitoring
concat
filemapper
firewall
flume
glance
haproxy
heat
horizon
inifile
kerberos
keystone:cern_newton
lemon
limits
logrotate
magnum
memcached
mistral
motd
mysql
network
neutron
nova
openstack_clients
openstacklib:newton
oslo
osrepos:fix-clouddev
octavia
psacct
puppetdbquery
rabbitmq
rsyslog
selinux
sssd
staging
stdlib
sudo
swap_file
sysctl
teigi
xinetd
"

# PUPPET_HOSTGROUPS holds the list of hostgroups we need to run the build(s)
PUPPET_HOSTGROUPS="
cloud_adm
cloud_blockstorage
cloud_compute:dev-env-fixes
cloud_container
cloud_dashboard
cloud_identity
cloud_image
cloud_monitoring
cloud_networking
cloud_orchestration
cloud_telemetry
cloud_workflow
"

# OS_PODS holds the list of pods to be started on 'launch'
OS_PODS=${OS_PODS:-mq1 keystone glance cinder neutron nova compute heat magnum mistral client}

# docker registry to push container images to (see push)
DOCKER_REGISTRY=${DOCKER_REGISTRY:-gitlab-registry.cern.ch/cloud}

# Checkout all the puppet modules and hostgroups
puppet_manifest_checkout() {
	if [ -e $CLOUDDEV_PUPPET ]; then
		echo "$CLOUDDEV_PUPPET exists, not touching..."
		return
	fi

	echo "To clone repositories, you need to have a Kerberos ticket"
	klist 2>&1 > /dev/null
	exit_on_err $?

	echo "Cloning puppet modules and hostgroups into ${CLOUDDEV_PUPPET}..."
	mkdir -p $CLOUDDEV_PUPPET
	for mod in $PUPPET_MODULES
	do
		cd $CLOUDDEV_PUPPET
		IFS=':' read -r module branch <<< "$mod"
		if [[ -z $branch ]]; then
			branch="master"
		fi
		echo "Cloning module ${module} on branch ${branch}"
		git clone -q https://:@gitlab.cern.ch:8443/ai/it-puppet-module-${module}.git
		exit_on_err $?
		ln -s it-puppet-module-$module/code $module
		cd it-puppet-module-$module
		exit_on_err $?
		git checkout -q $branch > /dev/null
		exit_on_err $? "It seems branch $branch does not exist"
		cd - > /dev/null
		branch=''
	done
	for hg in $PUPPET_HOSTGROUPS;
	do
		cd $CLOUDDEV_PUPPET
		IFS=':' read -r hostgroup branch <<< "$hg"
		if [[ -z $branch ]]; then
			branch="master"
		fi
		echo "Cloning hostgroup ${hostgroup} on branch ${branch}"
		git clone -q https://:@gitlab.cern.ch:8443/ai/it-puppet-hostgroup-${hostgroup}.git
		exit_on_err $?
		ln -s it-puppet-hostgroup-$hostgroup/code hg_$hostgroup
		cd it-puppet-hostgroup-$hostgroup
		exit_on_err $?
		git checkout -q $branch > /dev/null
		exit_on_err $? "It seems branch $branch does not exist"
		cd - > /dev/null
		branch=''
	done
	echo "All repositories have been cloned"
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
	wget https://dl.k8s.io/v$KUBERNETES_VERSION/kubernetes-server-linux-amd64.tar.gz
	tar zxf kubernetes-server-linux-amd64.tar.gz
	mv kubernetes/* .
	rm -rf kubernetes/
	tar zxf kubernetes-src.tar.gz ./hack ./cluster
	rm -rf kubernetes-server-linux-amd64.tar.gz kubernetes-src.tar.gz
	ln -s server _output
	wget http://pkg.cfssl.org/R1.2/cfssl_linux-amd64 -O $CLOUDDEV_KUB/_output/bin/cfssl
	wget http://pkg.cfssl.org/R1.2/cfssljson_linux-amd64 -O $CLOUDDEV_KUB/_output/bin/cfssljson
	chmod +x $CLOUDDEV_KUB/_output/bin/cfssl*
	exit_on_err $?
}

# start the kubernetes cluster
kubernetes_start() {
	# make sure the ebtables module is loaded
	sudo modprobe ebtables
	# iptables kernel module is not named the same on Ubuntu/CentOS
	if [ -e /etc/redhat-release ]; then
		sudo modprobe ip_tables
	else
		sudo modprobe iptables
	fi
	sudo modprobe ip6_tables

	# start the kube daemons
	cd $CLOUDDEV_KUB
	kubectl version > /dev/null 2>&1
	if [ $? -ne 0 ]; then
		pkill -f ".*local-up-cluster\.sh.*"
		sleep 2

		sudo PATH=$PATH ALLOW_PRIVILEGED="true" KUBE_ENABLE_CLUSTER_DNS="true" \
			setsid ./hack/local-up-cluster.sh -o _output/bin > /tmp/kubernetes-local.log 2>&1 &

		mkdir -p ~/.kube

		while [ ! -f /var/run/kubernetes/admin.kubeconfig ]
		do
			sleep 2
		done
		sleep 2

		cp /var/run/kubernetes/admin.kubeconfig ~/.kube/config

		echo 'Waiting for kubernetes start...'
		while ! kubectl get pod > /dev/null 2>&1
		do
			printf "."
			sleep 5
		done
		echo ""
		exit_on_err $?
		sudo chown -R $USER $CLOUDDEV_KUB
		exit_on_err $?
		echo "kubernetes started"

	fi
}

# start the base cluster pods
cluster_pod_base_start() {
	cluster_cleanup
	echo "starting the base pods (skydns, puppet, ceph)"
	# launch the pods
	cd $CLOUDDEV/kubernetes
	for z in puppet-pod.yaml puppet-svc.yaml landb-pod.yaml landb-svc.yaml ceph-pod.yaml wigner-pod.yaml; do
		kubectl create -f $z
	done

	while kubectl get pod --no-headers | grep -v "Running" > /dev/null 2>&1
	do
		printf "."
		sleep 2
	done
	echo ""

	for cluster in ceph wigner; do
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 auth add client.images -i /etc/ceph/${cluster}.client.images.keyring
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 auth add client.volumes -i /etc/ceph/${cluster}.client.volumes.keyring
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 osd pool create images 32
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 osd pool create volumes 32
		kubectl exec -it ${cluster} -c cephall -- /usr/bin/ceph --cluster ${cluster} --connect-timeout 10 osd pool create volumes-critical 32
	done

	echo "waiting for puppetdb to start..."
	while ! kubectl exec puppet -c puppetdb -- /usr/bin/curl -s http://localhost:8080/v3/facts > /dev/null 2>&1
	do
		printf "."
		sleep 2
	done
	echo ""
	exit_on_err $?
}

# start with a clean runtime
cluster_cleanup() {
	echo "cleaning up all kubernetes pods..."
	kubectl version > /dev/null 2>&1
	if [ $? -ne 0 ]; then
		return
	fi
	kubectl delete -f $CLOUDDEV/kubernetes --ignore-not-found --grace-period=5 --timeout=2s --cascade
	kubectl get pod | grep Terminating > /dev/null 2>&1
	echo "waiting for pods to be terminated..."
	while [ $? -eq 0 ]
	do
		printf "."
		sleep 2
		kubectl get pod | grep Terminating > /dev/null 2>&1
	done
	echo ""
	return 0
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
	while kubectl get pod --no-headers | grep -v "Running" > /dev/null 2>&1
	do
		printf "."
		sleep 2
	done
	echo ""
	# run puppet in the openstack pods, even if built from tag
	for pod in $OS_PODS
	do
		# run puppet on pod
		echo "Running Puppet on pod ${pod}..."
		sudo docker exec $(sudo docker ps | grep ${pod} | grep init | awk '{print $1}') puppet agent -t
		# Puppet return code:
		# 1 -> did not even start doing some things
		# 2 -> applied things, and everything went fine
		# 4 -> failures
		# 6 -> changes and failures
		local ret=$?
		if [[ $ret > 2  ]] || [[ $ret -eq 1 ]]; then
			echo "Puppet run for ${pod} failed with return code ${ret}."
			exit_on_err 1
		fi
		echo "Puppet run for ${pod} finished."
	done
	echo "OpenStack services are started"
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
	# Check for the CentOS version. It works on 7.3 (7.2 ?), but not 7.1
	ver=$(grep -oe '[0-9]\.[0-9]' /etc/centos-release)
	if [[ $ver != "7.3" ]]; then
		exit_on_err 1 "WARNING, the dev environment has been tested on 7.3, and it seems there are problems on previous version. You should update"
	fi

	# switch selinux in permissive
	# ldap container is having trouble otherwise
	setenforce 0
	sed -i 's#^SELINUX=.*#SELINUX=permissive#g' /etc/selinux/config

	echo "installing dependencies for centos..."
	sed -i '/^Defaults\s*requiretty/d' /etc/sudoers
	sudo yum install -y wget git etcd patch psmisc docker
	exit_on_err $?

	#sed -i 's#^DOCKER_STORAGE_OPTIONS.*#DOCKER_STORAGE_OPTIONS="--storage-driver=overlay"#g' /etc/sysconfig/docker-storage
	sed -i "s#^OPTIONS='--selinux-enabled #OPTIONS='#g" /etc/sysconfig/docker
	sed -i 's#^DOCKER_NETWORK_OPTIONS.*#DOCKER_NETWORK_OPTIONS="--dns 137.138.17.5 --bip 172.17.0.1/16"#g' /etc/sysconfig/docker-network

	systemctl stop NetworkManager firewalld
	systemctl disable NetworkManager firewalld
	iptables -F

	# launch docker
	systemctl enable docker
	systemctl start docker
}

exit_on_err() {
	if [[ $1 != 0 ]]; then
		# If there is an error msg, print it
		if [[ ! -z $2 ]]; then
			echo $2
		fi
		exit $1
	fi
}

# run tempest tests against the dev setup
tempest_run() {
	echo "running tempest tests..."
	kubectl exec -it client -c client /etc/tempest/run.sh
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
"
		exit 1
		;;
esac

