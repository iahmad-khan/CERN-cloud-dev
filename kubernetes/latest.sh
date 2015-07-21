#!/bin/sh

for c in keystone glance; do
	sed -e "s/puppetagent:latest/${c}/g" ${c}-pod.yaml > /tmp/${c}-pod.yaml
	kubectl.sh create -f /tmp/${c}-pod.yaml
	kubectl.sh create -f ${c}-svc.yaml

	kubectl.sh get pod ${c} | grep Pending > /dev/null 2>&1
	while [ $? -eq 0 ]
	do
		kubectl.sh get pod ${c} | grep Pending > /dev/null 2>&1
	done
	kubectl.sh exec -p ${c} -c ${c} -- puppet agent -t
done
