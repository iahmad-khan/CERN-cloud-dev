#!/bin/sh

for dir in *; do
	if [ -d ${dir} ] && [ -e ${dir}/Dockerfile ]; then
		sudo docker build -t rochaporto/${dir} ${dir}
	fi
done
