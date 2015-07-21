## Table of Contents

**[Overview](#overview)**
**[Requirements](#requirements)**
**[Setup](#setup)**
**[Quick Start](#quick-start)**

* [Start from latest](#start-from-latest)
* [Build node from scratch](#build-node-from-scratch)

**[Common Operations](#common-operations)**

### Overview

Goals:
* simulate the CERN cloud environment in containers running on your local box
* no external dependencies, should work even offline

What it provides:
* puppet node (master, puppetdb, teigi), mounting your workspace so that the master exposes your local manifests+hiera
* controller node (mysql, rabbitmq, ldap)
* ceph node (mon + osd)
* one additional pod per service (keystone, glance, cinder, ...)
* a dns service (addon to kubernetes)

### Requirements

Basic knowledge of kubernetes (what is a pod, what is a service, ...).

### Setup

Soon.

### Quick Start

```
./start.sh
kubectl.sh get pod
POD              IP             CONTAINER(S)   IMAGE(S)                                         HOST                  LABELS                                                STATUS    CREATED      MESSAGE
ceph                                                                                            127.0.0.1/127.0.0.1   name=ceph                                             Running   26 minutes   
                                cephall        docker-reg.cern.ch:5000/cephdemo                                                                                             Running   26 minutes   
controller       172.17.0.206                                                                   127.0.0.1/127.0.0.1   name=controller                                       Running   26 minutes   
                                rabbitmq       rabbitmq                                                                                                                     Running   26 minutes   
                                mysql          mysql                                                                                                                        Running   26 minutes   
                                ldap           docker-reg.cern.ch:5000/ldap                                                                                                 Running   26 minutes   
puppet           172.17.0.205                                                                   127.0.0.1/127.0.0.1   name=puppet                                           Running   26 minutes   
                                puppetdb       docker-reg.cern.ch:5000/puppetdb                                                                                             Running   25 minutes   
                                puppetmaster   docker-reg.cern.ch:5000/puppetmaster:latest                                                                                  Running   25 minutes   
                                teigi          docker-reg.cern.ch:5000/teigi                                                                                                Running   25 minutes   
kube-dns-ywmmu   172.17.0.207                                                                   127.0.0.1/127.0.0.1   k8s-app=kube-dns,kubernetes.io/cluster-service=true   Running   26 minutes   
                                skydns         gcr.io/google_containers/skydns:2015-03-11-001                                                                               Running   26 minutes   
                                etcd           gcr.io/google_containers/etcd:2.0.9                                                                                          Running   26 minutes   
                                kube2sky       gcr.io/google_containers/kube2sky:1.3                                                                                        Running   26 minutes   
```

As we cannot control the IPs containers get, we rely on kubernetes 'services' and the built-in DNS service, so we can reference each 'pod' by its name as its hostname.
```
kubectl.sh get service

NAME            LABELS                                                              SELECTOR           IP(S)        PORT(S)
controller      name=controller                                                     name=controller    10.0.0.123   3306/TCP
                                                                                                                    389/TCP
kube-dns        k8s-app=kube-dns,kubernetes.io/cluster-service=true,name=kube-dns   k8s-app=kube-dns   10.0.0.10    53/UDP
                                                                                                                    53/TCP
kubernetes      component=apiserver,provider=kubernetes                             <none>             10.0.0.2     443/TCP
kubernetes-ro   component=apiserver,provider=kubernetes                             <none>             10.0.0.1     80/TCP
puppet          name=puppet                                                         name=puppet        10.0.0.233   8140/TCP
```

With all the required basic infrastructure set, you can now either start from the 'latest' pre-built image for all nodes, or build nodes from scratch.

#### Start from latest

This is the most common use case. You want to quickly get an environment that allows you to work on any of the services.
```
cd kubernetes
./latest.sh
```

You can then go ahead with changes in your local puppet manifests, and then run puppet in the corresponding node:
```
kubectl.sh exec -it -p keystone -c keystone /bin/bash
puppet agent -t
```

#### Build node from scratch

You want to test a node builds properly from scratch (example for keystone):
```
cd kubernetes
kubectl.sh create -f keystone-pod.yaml
kubectl.sh create -f keystone-svc.yaml
kubectl.sh exec -it -p keystone -p keystone /bin/bash
puppet agent -t
```

### Common Operations

#### Cleanup and rebuilt the environment
```
cd kubernetes
./cleanup.sh
./start.sh
```
