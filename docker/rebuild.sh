#!/bin/bash
if [ "$1" != "push" ]; then
	echo "REBUILD: Building locally only... pass 'push' as arg to also push to registry"
else
	echo "REBUILD: Will push images to registry"
fi

for dir in base puppetbase puppetdb puppetagent puppetmaster ../teigi; do
	img=$(echo ${dir} | cut -d'/' -f 2)
	echo "REBUILD: Building image ${img}"
	sudo docker build -t docker.cern.ch/cloud-infrastructure/${img} ${dir}
	if [ $? != 0 ]; then
		echo "REBUILD: Docker build of ${dir} failed"
		exit $?
	fi
	if [ "$1" == "push" ]; then
		sudo docker push docker.cern.ch/cloud-infrastructure/${img}
	fi
done
