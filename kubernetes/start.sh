#!/bin/sh

for z in dns-hack.json skydns-rc.yaml skydns-svc.yaml puppet-pod.yaml puppet-svc.yaml; do
	kubectl.sh create -f $z
done
