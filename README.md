[![Build Status](https://gitlab.cern.ch/cloud-infrastructure/cloud-dev/ci_settings/edit#)](https://gitlab.cern.ch/cloud-infrastructure/cloud-dev/ci_settings/edit#)

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
cd ~/ws
# You need a Kerberos ticket to get the code
git clone https://:@gitlab.cern.ch:8443/cloud-infrastructure/cloud-dev.git
export CLOUDDEV=~/ws/cloud-dev
export CLOUDDEV_PUPPET=~/ws/cern-puppet
export CLOUDDEV_KUB=~/ws/kubernetes
cd scripts
./cci-dev.sh centos
```

Otherwise here are the detailed steps:
```
sed -i '/^Defaults\s*requiretty/d' /etc/sudoers
sudo yum install -y wget git vim docker etcd golang patch psmisc
sed -i "s/^# INSECURE_REGISTRY.*/INSECURE_REGISTRY='--insecure-registry docker-reg.cern.ch:5000'/g" /etc/sysconfig/docker
sed -i "s/^OPTIONS.*/OPTIONS='--storage-driver overlay'/g" /etc/sysconfig/docker
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
  prepare      Prepare the dev workspace (fetch kubernetes, puppet modules, ...)
  restart      Cleanup any running containers and recreate the base containers (skydns, puppet, ceph)
  launch [tag] Launch the openstack containers, optionally from 'tag' (docker image tag) - otherwise full puppet run
  last         Launch the 'last' built openstack containers - wrapper for 'launch last'
  tag [tag]    Tag all running containers with the give tag (commit first, then tag)
  push [tags]  (done by CI only) Push the current OS containers as a new image, optionally tagging with the given list
  cleanup      Cleanup any running containers so we get a clean set
  centos       Install required dependencies for CentOS
  tempest      Run tempest tests against the dev environment

Required environment settings: CLOUDDEV, CLOUDDEV_PUPPET, CLOUDDEV_KUB
Example:
export CLOUDDEV=~/ws/cloud-dev
export CLOUDDEV_PUPPET=~/ws/cern-puppet
export CLOUDDEV_KUB=~/ws/kubernetes
```

First prepare your development environment (you only need to do this once):
```
export CLOUDDEV=~/ws/cloud-dev
export CLOUDDEV_PUPPET=~/ws/cern-puppet
export CLOUDDEV_KUB=~/ws/kubernetes

cd ~/ws
# You need a Kerberos ticket to get the code
git clone https://:@gitlab.cern.ch:8443/cloud-infrastructure/cloud-dev.git
cd cloud-dev/scripts
./cci-dev.sh prepare
```

After this you'll be relaunching the containers from scratch quite often:
```
./cci-dev.sh restart
./cci-dev.sh launch last
```

Note we launched from the 'last' tag, which launches containers from a pre-built image and runs puppet from there (much faster). These 'last' images are maintained by the CI system, updated when things get merged to master.

If you really want to rebuild all the nodes from scratch (full puppet runs), trigger launch with no args.
```
./cci-dev.sh launch

```

You can then save these containers as image in you local repository with the command "tag". So that next run will start from this last state.
```
./cci-dev.sh tag mylatest-20160201
```
To start from a tag, you can do:
```
./cci-dev.sh launch mylatest-20160201
```


With an environment set, you can *login* to a container and run the usual commands:
```
export PATH=$PATH:$CLOUDDEV_KUB/_output/local/bin/linux/amd64
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

## Common Use Cases

### Relaunching a failing build/commit

After each build the CI system adds an additional tag to each image with the commit ID. This allows you to launch an
environment corresponding to the failing build (useful for debugging).
```
cd scripts
./cci-dev.sh restart
./cci-dev.sh launch <commit-id>
```

## Tempest

The client node gets a tempest checkout in /tempest. You can use cci-dev.sh to run the tests (uses config in cloud-dev/tempest):
```
cd scripts
./cci-dev.sh tempest
...
```

The list of tests enabled is in cloud-dev/tempest/tempest.list.


## Maintenance

### Redeploy the jenkins master

Jenkins resources are held under the *Cloud CI* tenant.

There should be a volume name jenkins-config already holding the master configuration.

You can confidently recreate the heat stack as the configuration is kept persistent in the volume:
```
cd $CLOUDDEV/heat
heat stack-create cci-jenkins -f jenkins.yaml -e jenkins-env-clouddev.yaml
```

### Jenkins / Gitlab integration

Same recipe as in:
http://jenkinsdocs.web.cern.ch/jenkinsdocs/chapters/demos/jenkinsdocs-build.html

and:
http://jenkinsdocs.web.cern.ch/jenkinsdocs/chapters/revision-control-systems/git/gitlab.html

### Rebuilding the 'latest' mysql

We use a clone of the mysql Dockerfile, as we need to have /var/lib/mysql/data committed with the container (upstream it's a volume).

This might change when 'docker volume ...' gets released, but for now it's the only way to persist the db data.

If we ever need to update that base mysql image, it's done as in:
```
cd docker/mysql
sudo docker build -t docker-reg.cern.ch:5000/mysql:latest .
sudo docker push docker-reg.cern.ch:5000/mysql:latest
```

## Troubleshooting

### dns lookup failing (after configuring kube-dns in resolv.conf)

This seems to be due to multicast dns queries. Solution in ubuntu:
```
apt-get remove libnss-mdns
```

which also updates /etc/nsswitch.conf appropriately.
