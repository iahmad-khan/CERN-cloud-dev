#!/bin/sh

sudo killall -9 kube-apiserver kube-controller-manager kube-proxy kube-scheduler kubelet etcd
sudo docker ps --all | awk '{print $1}' | xargs sudo docker rm -f
