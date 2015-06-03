# Setup

## Host / dev machine setup
```
vim /etc/default/docker
DOCKER_OPTS="--dns 137.138.17.5 --dns 8.8.8.8"

sudo ln -s ~ws/cloud-dev/puppet /opt/puppet
sudo ln -s ~ws/cloud-dev/facter /opt/facter
sudo ln -s ~ws/cern /opt/puppet-modules
sudo chown -h ricardo:ricardo /opt/puppet /opt/facter /opt/puppet-modules
ls -l /opt/puppe*
lrwxrwxrwx 1 ricardo ricardo 33 May 29 14:21 puppet -> /home/ricardo/ws/cloud-dev/puppet
lrwxrwxrwx 1 ricardo ricardo 33 May 29 14:21 facter -> /home/ricardo/ws/cloud-dev/facter
lrwxrwxrwx 1 ricardo ricardo 21 May 29 14:21 puppet-modules -> /home/ricardo/ws/cern
```

Using Ubuntu Vivid 15.04 (or probably any systemd based system)? Docker 1.5 at least is not ready for it. Here's a workaround:
http://nknu.net/how-to-configure-docker-on-ubuntu-15-04/
```
sudo vim /lib/systemd/system/docker.service
[Service]
EnvironmentFile=/etc/default/docker
ExecStart=/usr/bin/docker -d -H fd:// $DOCKER_OPTS
```

# Lauching the base cluster setup
```
./kubernetes/start.sh
```

# TODO

## Specify required container privileges individually

To get systemd to run in a container we need to add a few capabilities in the pod config:
```
spec:
  containers:
    - name: keystone
      capabilities:
        add: [SYS_ADMIN]
```

Full list in here:
https://github.com/GoogleCloudPlatform/kubernetes/blob/master/docs/containers.md

Unclear right now which capabilities in addition are required, but running on a privileged container does work (though not good). For this to work, edit ./hack/local-up-cluster.sh and add --allow_privileged to both kubelet and kube-apiserver.

# Common Operations

## Start a shell on a running container

kubectl.sh exec -p <pod> -c <container> -i -t -- /bin/bash

## Kubernetes fails to start, running already

}ERROR starting API SERVER, exiting.  Some host on 127.0.0.1 is serving already on 8080

killall -9 kube-apiserver kube-controller-manager kube-proxy kube-scheduler etcd
./hack/local-up-cluster.sh

## Delete all non running containers

sudo docker ps -f 'status=exited' | awk '{print $1}' | xargs sudo docker rm

## httpd failing to install with cap_set_file error

Seen in ubuntu trusty, seems to be related to AUFS and missing CONFIG_AUFS_XATTR in the kernel (3.16.x). Upgrading to Vivit and kernel 3.19 makes it look like:
```
grep -R CONFIG_AUFS_XATTR /boot/config-3.19.0-18-generic
CONFIG_AUFS_XATTR=y
```

and it works.
