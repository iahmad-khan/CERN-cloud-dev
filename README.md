# Setup

vim /etc/default/docker
DOCKER_OPTS="--dns 137.138.17.5 --dns 8.8.8.8"

# Common Operations

## Start a shell on a running container

kubectl.sh exec -p <pod> -c <container> -i -t -- /bin/bash

## Kubernetes fails to start, running already

}ERROR starting API SERVER, exiting.  Some host on 127.0.0.1 is serving already on 8080

killall -9 kube-apiserver kube-controller-manager kube-proxy kube-scheduler etcd
./hack/local-up-cluster.sh

## Delete all non running containers

sudo docker ps -f 'status=exited' | awk '{print $1}' | xargs sudo docker rm
