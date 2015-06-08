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

## Common Operations

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

## Generating the fake root CA

The CA is committed to the git repo, so you shouldn't have to do this again. In any case:
```
openssl genrsa -out root-ca.key 2048

openssl req -x509 -new -nodes -key root-ca.key -days 36000 -out root-ca.pem
Country Name (2 letter code) [AU]:CH
State or Province Name (full name) [Some-State]:Geneva
Locality Name (eg, city) []:
Organization Name (eg, company) [Internet Widgits Pty Ltd]:CERN
Organizational Unit Name (eg, section) []:OIS
Common Name (e.g. server FQDN or YOUR name) []:devca
```

You can store it in teigi/certs/root-ca... to have it propagated.

## Generating new certificates using the root CA

Many services require these. Usually you'll fetch them via teigi, so save the output in teigi/certs/... appropriately.

If you need alternate names, edit openssl.conf and uncomment subjectAltName and the alt_names lines with appropriate values.

```
vim openssl.conf
...
[ v3_req ]

# Extensions to add to a certificate request

basicConstraints = CA:FALSE
keyUsage = nonRepudiation, digitalSignature, keyEncipherment
# subjectAltName = @alt_names

# [alt_names]

# DNS.1 = keystone.default.kubdomain.local
# DNS.2 = controller.default.kubdomain.local

```

```
# openssl genrsa -out keystone.key 2048

# openssl req -new -key keystone.key -out keystone.csr -config openssl.conf
Country Name (2 letter code) [CH]:
State or Province Name (full name) [Geneva]:
Locality Name (eg, city) []:
Organization Name (eg, company) [CERN]:
Organizational Unit Name (eg, section) [OIS]:
Common Name (e.g. server FQDN or YOUR name) []:keystone
Email Address []:

# openssl x509 -req -in keystone.csr -CA root-ca.pem -CAkey root-ca.key -CAcreateserial -out keystone.pem -days 36000 -extensions v3_req -extfile openssl.conf

# openssl x509 -in keystone.pem -text
...
    Signature Algorithm: sha256WithRSAEncryption
        Issuer: C=CH, ST=Geneva, O=CERN, OU=OIS, CN=keystone
        Validity
...
        X509v3 extensions:
            X509v3 Basic Constraints: 
                CA:FALSE
            X509v3 Key Usage: 
                Digital Signature, Non Repudiation, Key Encipherment
            X509v3 Subject Alternative Name: 
                DNS:keystone.default.kubdomain.local, DNS:controller.default.kubdomain.local, DNS:keystone, DNS:controller
```

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

