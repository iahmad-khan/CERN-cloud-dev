#!/bin/sh

for z in dns-hack.json skydns-rc.yaml skydns-svc.yaml puppet-pod.yaml puppet-svc.yaml controller-pod.yaml controller-svc.yaml ceph-pod.yaml; do
	kubectl create -f $z
done

kubectl get pod ceph | grep Pending > /dev/null 2>&1
while [ $? -eq 0 ]
do
  echo "waiting for ceph pod to be ready..."
  sleep 2
  kubectl get pod ceph | grep Pending > /dev/null 2>&1
done

kubectl exec -it -p ceph -c cephall -- /usr/bin/ceph --connect-timeout 10 auth add client.images -i /etc/ceph/ceph.client.images.keyring
kubectl exec -it -p ceph -c cephall -- /usr/bin/ceph --connect-timeout 10 auth add client.volumes -i /etc/ceph/ceph.client.volumes.keyring
kubectl exec -it -p ceph -c cephall -- /usr/bin/ceph --connect-timeout 10 osd pool create images 64
kubectl exec -it -p ceph -c cephall -- /usr/bin/ceph --connect-timeout 10 osd pool create volumes 64
