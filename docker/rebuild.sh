#!/bin/sh

for dir in base puppetbase puppetdb puppetagent puppetmaster; do
	sudo docker build -t rochaporto/${dir} ${dir}
done
