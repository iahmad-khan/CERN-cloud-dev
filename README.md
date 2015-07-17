# Overview

Simulates the CERN cloud environment in containers running on a local box.

It does this by providing:
* puppet node, which includes a master, puppetdb, mock of teigi, ldap, ...
* controller node, with keystone, cinder, glance, ...
* compute nodes, running nova compute, etc

Your workspace (puppet modules, puppet configuration, etc) are 'mounted' on each node, so changes are seen live.

This allows you to run a full CERN cloud environment in a normal desktop (thanks shared kernel!).

# Quick Start

A useful script launches the required starting pods (dns and puppet):
```
./start.sh
kubectl.sh get pod
POD              IP            CONTAINER(S)   IMAGE(S)                                         HOST                  LABELS                                                STATUS    CREATED         MESSAGE
kube-dns-wnkt0   172.17.0.43                                                                   127.0.0.1/127.0.0.1   k8s-app=kube-dns,kubernetes.io/cluster-service=true   Running   About an hour   
                               skydns         gcr.io/google_containers/skydns:2015-03-11-001                                                                               Running   About an hour   
                               kube2sky       gcr.io/google_containers/kube2sky:1.3                                                                                        Running   About an hour   
                               etcd           gcr.io/google_containers/etcd:2.0.9                                                                                          Running   About an hour   
puppet           172.17.0.44                                                                   127.0.0.1/127.0.0.1   name=puppet                                           Running   About an hour   
                               puppetdb       rochaporto/puppetdb                                                                                                          Running   About an hour   
                               puppetmaster   rochaporto/puppetmaster:latest                                                                                               Running   About an hour   
                               teigi          rochaporto/teigi                                                                                                             Running   About an hour  
```

You can then launch the pods you're interested to work with. Example for the controller:
```
kubectl.sh create -f controller-pod.yaml
```

A pod is a group of containers sharing the same networking environment (IP, etc), so they can reference localhost between themselves.

Currently you need a workaround in the kubernetes local cluster setup (add cluster_dns, cluster_domain and host-network-sources):
```
vim kubernetes/hack/local-up-cluster.sh
...
  sudo -E "${GO_OUT}/kubelet" \
    --v=${LOG_LEVEL} \
    --chaos_chance="${CHAOS_CHANCE}" \
    --container_runtime="${CONTAINER_RUNTIME}" \
    --hostname_override="127.0.0.1" \
    --address="127.0.0.1" \
    --api_servers="${API_HOST}:${API_PORT}" \
    --auth_path="${KUBE_ROOT}/hack/.test-cmd-auth" \
    --port="$KUBELET_PORT" >"${KUBELET_LOG}" 2>&1 \
    --cluster_dns=10.0.0.10 \
    --cluster_domain=kubdomain.local \
    --host-network-sources=* \
    --allow_privileged &

```

## Common Operations

### Initialization

Soon all will be integrated in the setup, but for now:
```
ldapadd -h 10.0.0.154 -w 123456 -D 'cn=admin,dc=default,dc=kubdomain,dc=local' -c -f docker/ldap/ldap.ldif
```

### Launching a new pod
Replace 'controller' with the actual pod you want to launch.
```
kubectl.sh create -f controller-pod.yaml
```

### Connecting to a specific container
Replace 'controller' and 'keystone' with the pod/container you want to connect to.
```
kubectl.sh exec -ti -p controller -c keystone -- /bin/bash
```

Due to a [bug in kubernetes](https://github.com/GoogleCloudPlatform/kubernetes/issues/9180) we need a workaround for now as the session times out after ~5 minutes.

Replace 'admin' with the actual container name.
```
sudo docker exec -it $(sudo docker ps | grep admin | awk '{print $1}') /bin/bash
```

# Maintenance

## Generating new certificates (using the puppet ca)

kubectl.sh exec -it puppet -c master /bin/bash
puppet cert --allow-dns-alt-names generate glance.default.kubdomain.local --dns_alt_names=glance,glance.default
puppet cert --allow-dns-alt-names sign glance.default.kubdomain.local
exit

## Setting up the docker private registry

The images are stored in a CEPH volume (rbritoda/docker-images), mount it on the host under /docker-images.

Then the easiest is to use the built-in docker registry image (we can do proper auth later).
```
docker run --privileged -e LOGLEVEL=debug -e SETTINGS_FLAVOR=local -e STORAGE_PATH=/docker-images -v /docker-images:/docker-images -p 5000:5000 registry
```

# Setup

## Host / dev machine setup
```
vim /etc/default/docker
DOCKER_OPTS="--dns 137.138.17.5 --dns 8.8.8.8"

sudo ln -s ~ws/cloud-dev/puppet /opt/puppet
sudo ln -s ~ws/cloud-dev/puppetssl /opt/puppetssl
sudo ln -s ~ws/cloud-dev/facter /opt/facter
sudo ln -s ~ws/cloud-dev/ceph /opt/ceph
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

Setup the docker-reg.cern.ch private registry. As it's no auth for now, you need to add the following:
```
sudo vim /etc/sysconfig/docker
# Modify these options if you want to change the way the docker daemon runs
OPTIONS='--selinux-enabled --insecure-registry docker-reg.cern.ch:5000'
```

On Ubuntu do this on /etc/default/docker and DOCKER_OPTS.

# Troubleshooting

## Kubernetes fails to start, running already

}ERROR starting API SERVER, exiting.  Some host on 127.0.0.1 is serving already on 8080

killall -9 kube-apiserver kube-controller-manager kube-proxy kube-scheduler etcd
./hack/local-up-cluster.sh

## My cluster is misbehaving, how to i clean it all up and start from scratch?
```
./cleanup.sh
```

## httpd failing to install with cap_set_file error

Seen in ubuntu trusty, seems to be related to AUFS and missing CONFIG_AUFS_XATTR in the kernel (3.16.x). Upgrading to Vivit and kernel 3.19 makes it look like:
```
grep -R CONFIG_AUFS_XATTR /boot/config-3.19.0-18-generic
CONFIG_AUFS_XATTR=y
```

and it works.


## Querying the ldap directory
```
ldapsearch -h 10.0.0.154 -w 123456 -D 'cn=admin,dc=default,dc=kubdomain,dc=local' -b 'dc=default,dc=kubdomain,dc=local' '(objectclass=*)'
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

