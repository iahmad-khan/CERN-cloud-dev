#!/bin/sh

for z in dns-hack.json skydns-rc.yaml skydns-svc.yaml puppet-pod.yaml puppet-svc.yaml controller-pod.yaml controller-svc.yaml ceph-pod.yaml; do
	kubectl.sh create -f $z
done

kubectl.sh get pod | grep Pending > /dev/null 2>&1
while [ $? -eq 0 ]
do
	sleep 3
	kubectl.sh get pod | grep Pending > /dev/null 2>&1
done

../hack/push-ldap-users.sh
ceph auth add client.images -i ../ceph/ceph.client.images.keyring
ceph osd pool create images 64
ceph osd pool create volumes 64
