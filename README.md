# Overview

Provides a lightweight container based environment for local deployments of the CERN cloud infrastructure.

It does this by providing:
* puppet node, which includes a master, puppetdb, mock of teigi, ldap, ...
* controller node, with keystone, cinder, glance, ...
* compute nodes, running nova compute, etc

The important files (puppet configuration, puppet modules, ...) are local to the host, and 'mounted' in the containers so that you can easily update them in your box and have the changes available in all container nodes immediately.

## Example

The setup is done using kubernetes, which introduces the notion of a pod as a group of containers. The relevant thing here is that all containers in a pod share the same networking features (ip, etc) so even if services are run in individual containers, they still share the same IP address and can reference themselves are localhost.

In this case we have a puppet pod (with master, puppetdb and teigi containers), a controller pod (with a keystone container), and a client pod (with an admin container).
```
kubectl.sh get pod
POD              IP            CONTAINER(S)   IMAGE(S)                                         HOST                  LABELS                                                STATUS    CREATED         MESSAGE
client           172.17.0.45                                                                   127.0.0.1/127.0.0.1   name=client                                           Running   About an hour   
                               admin          rochaporto/puppetagent:latest                                                                                                Running   About an hour   
kube-dns-wnkt0   172.17.0.43                                                                   127.0.0.1/127.0.0.1   k8s-app=kube-dns,kubernetes.io/cluster-service=true   Running   About an hour   
                               skydns         gcr.io/google_containers/skydns:2015-03-11-001                                                                               Running   About an hour   
                               kube2sky       gcr.io/google_containers/kube2sky:1.3                                                                                        Running   About an hour   
                               etcd           gcr.io/google_containers/etcd:2.0.9                                                                                          Running   About an hour   
puppet           172.17.0.44                                                                   127.0.0.1/127.0.0.1   name=puppet                                           Running   About an hour   
                               teigi          rochaporto/teigi                                                                                                             Running   About an hour   last termination: exit code 255
                               puppetdb       rochaporto/puppetdb                                                                                                          Running   About an hour   
                               puppetmaster   rochaporto/puppetmaster:latest            
```

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

# Generating the CA cert
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

# Generating new certificates
```
# openssl genrsa -out keystone.key 2048
# openssl req -new -key keystone.key -out keystone.csr
Country Name (2 letter code) [AU]:CH
State or Province Name (full name) [Some-State]:Geneva
Locality Name (eg, city) []:
Organization Name (eg, company) [Internet Widgits Pty Ltd]:CERN
Organizational Unit Name (eg, section) []:OIS
Common Name (e.g. server FQDN or YOUR name) []:keystone
Email Address []:

# openssl x509 -req -in keystone.csr -CA root-ca.pem -CAkey root-ca.key -CAcreateserial -out keystone.pem -days 36000
# openssl x509 -in keystone.pem -text
Certificate:
    Data:
        Version: 1 (0x0)
        Serial Number: 9416355589148117231 (0x82ad9d84b1f008ef)
    Signature Algorithm: sha256WithRSAEncryption
        Issuer: C=CH, ST=Geneva, O=CERN, OU=OIS, CN=devca
        Validity
            Not Before: Jun  3 20:03:48 2015 GMT
            Not After : Dec 26 20:03:48 2113 GMT
        Subject: C=CH, ST=Geneva, O=CERN, OU=OIS, CN=keystone
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
