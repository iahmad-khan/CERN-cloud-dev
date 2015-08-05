## Overview

Goals:
* simulate the CERN cloud environment in containers running on your local box
* no external dependencies, should work even offline

What it provides:
* puppet node (master, puppetdb, teigi), mounting your workspace so that the master exposes your local manifests+hiera
* controller node (mysql, rabbitmq, ldap)
* ceph node (mon + osd)
* one additional pod per service (keystone, glance, cinder, ...)
* a dns service (addon to kubernetes)

## Requirements

Basic knowledge of kubernetes (what is a pod, what is a service, ...).

## Setup

If you're in CentOS 7, the following command should help you (we use it in the CI setup):
```
cd scripts
./cci-dev.sh centos
```

Otherwise here are the detailed steps:
```
sed -i '/^Defaults\s*requiretty/d' /etc/sudoers
sudo yum install -y wget git vim docker etcd golang patch psmisc
sed -i "s/^# INSECURE_REGISTRY.*/INSECURE_REGISTRY='--insecure-registry docker-reg.cern.ch:5000'/g" /etc/sysconfig/docker
systemctl restart docker
```

## Quick Start

There are 3 relevant locations in the workspace:
* CLOUDDEV is where you clone the cloud-dev repo
* CLOUDDEV_PUPPET is the directory where the CERN puppet modules will be cloned
* CLOUDDEV_KUB is where the kubernetes installation will be placed

The script *scripts/cci-dev.sh* should help with the setup commands.

```
cloud-dev/scripts$ ./cci-dev.sh 
Usage: cci-dev COMMAND
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
```

First prepare your development environment (you only need to do this once):
```
export CLOUDDEV=~/ws/cloud-dev
export CLOUDDEV_PUPPET=~/ws/cloud-dev/cern-puppet
export CLOUDDEV_KUB=~/ws/cloud-dev/kubernetes
./cci-dev prepare
```

After this you'll be relaunching the containers from scratch quite often:
```
./cci-dev restart
./cci-dev latest
```

Once you've done this you can *login* to a container, and run the usual commands:
```
kubectl exec -it -p keystone -c keystone -- /bin/bash
[root@keystone /]# puppet agent -t
```

Whatever changes you do to the puppet modules in CLOUDDEV_PUPPET are seen immediately.


### Test the environment

Let's try to create an image in glance, the client is available in the glance pod/container:
```
kubectl.sh exec -it -p glance -c glance -- /bin/bash
[root@glance /]# . root/openrc
[root@glance /]# wget http://download.cirros-cloud.net/0.3.3/cirros-0.3.3-x86_64-disk.img
[root@glance /]# glance image-create --name cirros --disk-format aki --file cirros-0.3.3-x86_64-disk.img
[root@glance /]# glance image-list
+--------------------------------------+--------+-------------+------------------+----------+--------+
| ID                                   | Name   | Disk Format | Container Format | Size     | Status |
+--------------------------------------+--------+-------------+------------------+----------+--------+
| 64a34d63-0f85-4fad-9324-ee58b62d9868 | cirros | aki         | aki              | 13200896 | active |
+--------------------------------------+--------+-------------+------------------+----------+--------+
```

And check the data is actually in our local ceph container:
```
kubectl.sh exec -it -p ceph -c cephall -- /bin/bash
root@ /# rados ls -p images
rbd_data.10155d46d745.0000000000000001
rbd_directory
rbd_id.64a34d63-0f85-4fad-9324-ee58b62d9868
rbd_data.10155d46d745.0000000000000000
rbd_header.10155d46d745
```

## Common Operations

### Redeploy the jenkins master

Jenkins resources are held under the *Cloud CI* tenant.

There should be a volume name jenkins-config already holding the master configuration.

You can confidently recreate the heat stack as the configuration is kept persistent in the volume:
```
cd $CLOUDDEV/heat
heat stack-create cci-jenkins -f jenkins.yaml -e jenkins-env-clouddev.yaml
```
