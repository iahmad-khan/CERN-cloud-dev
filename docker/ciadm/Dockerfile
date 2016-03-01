FROM centos:7

MAINTAINER Bertrand NOEL, bertrand.noel@cern.ch

# cern base dependencies
ADD http://linux.web.cern.ch/linux/centos7/CentOS-CERN.repo /etc/yum.repos.d/CentOS-CERN.repo
RUN /usr/bin/rpm --import http://linuxsoft.cern.ch/cern/centos/7.1/os/x86_64/RPM-GPG-KEY-cern

# openstack clients
RUN echo $'\n\
[cci7-openstack-clients-qa] \n\
name=CERN rebuilds for OpenStack clients - QA \n\
baseurl=http://linuxsoft.cern.ch/internal/repos/openstackclients7-liberty-qa/x86_64/os/ \n\
enabled=1 \n\
gpgcheck=0 \n\
priority=5 \n'\
>> /etc/yum.repos.d/openstackclients7-liberty-qa.repo

RUN yum install -y \
	centos-release-openstack-liberty

RUN yum install -y \
	python-openstackclient \
	python2-magnumclient

# docker client (upstream)
RUN echo $'\n\
[dockerrepo] \n\
name=Docker Repository \n\
baseurl=https://yum.dockerproject.org/repo/main/centos/$releasever/ \n\
enabled=1 \n\
gpgcheck=1 \n\
gpgkey=https://yum.dockerproject.org/gpg \n'\
>> /etc/yum.repos.d/docker.repo

RUN yum install -y \
	docker-engine
