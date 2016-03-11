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

Prepare the environment (might be handy to put this somewhere to source later).
```
export CLOUDDEV=~/ws/cloud-dev
export CLOUDDEV_PUPPET=~/ws/cern-puppet
export CLOUDDEV_KUB=~/ws/kubernetes
export PATH=$PATH:$CLOUDDEV_KUB/_output/local/bin/linux/amd64
```

Fetch the code:
```
mkdir ~/ws
cd ~/ws
git clone https://:@gitlab.cern.ch:8443/cloud-infrastructure/cloud-dev.git
```

Setup the local environment (handy command for CentOS, used for CI too):
```
cd ~/ws/cloud-dev/scripts
./cci-dev.sh centos
```

Check inside the cci-dev.sh script (centos_install function) for details on how to install in other environments.

The script provides some additional details on available commands:
```
./cci-dev.sh
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

```

## Quick Start

First prepare your development environment (you only need to do this once):
```
cd ~/wscloud-dev/scripts
./cci-dev.sh prepare
```

After this you'll be relaunching the containers from scratch quite often:
```
./cci-dev.sh restart
./cci-dev.sh launch
```

With an environment set, you can *login* to a container and run the usual commands:
```
kubectl exec -it keystone -c keystone /bin/bash
[root@keystone /]# puppet agent -t
```

Whatever changes you do to the puppet modules in CLOUDDEV_PUPPET are seen immediately.

### Test the environment

Let's try to create an image in glance, the client is available in the glance pod/container:
```
kubectl exec -it glance -c glance /bin/bash
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
kubectl.sh exec -it ceph -c cephall /bin/bash
root@ /# rados ls -p images
rbd_data.10155d46d745.0000000000000001
rbd_directory
rbd_id.64a34d63-0f85-4fad-9324-ee58b62d9868
rbd_data.10155d46d745.0000000000000000
rbd_header.10155d46d745
```

## Common Use Cases

## Tempest

The client node gets a tempest checkout in /tempest. You can use cci-dev.sh to run the tests (uses config in cloud-dev/tempest):
```
cd scripts
./cci-dev.sh tempest
...
```

The list of tests enabled is in cloud-dev/tempest/tempest.list.


## Maintenance

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
