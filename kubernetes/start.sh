#!/bin/sh

for z in dns-hack.json skydns-rc.yaml skydns-svc.yaml puppet-pod.yaml puppet-svc.yaml controller-pod.yaml controller-svc.yaml ceph-pod.yaml; do
	kubectl.sh create -f $z
done

ceph auth add client.images -i ../ceph/ceph.client.images.keyring
ceph auth add client.volumes -i ../ceph/ceph.client.volumes.keyring
ceph osd pool create images 64
ceph osd pool create volumes 64
