FROM centos:latest

MAINTAINER Bertrand NOEL, bertrand.noel@cern.ch

# Repos
ADD http://linux.web.cern.ch/linux/centos7/CentOS-CERN.repo /etc/yum.repos.d/CentOS-CERN.repo
RUN /usr/bin/rpm --import http://linuxsoft.cern.ch/cern/centos/7.1/os/x86_64/RPM-GPG-KEY-cern
ADD cci7-openstack-clients-qa.repo /etc/yum.repos.d/

# Openstack clients
RUN yum install -y centos-release-openstack-liberty
RUN yum install -y python-openstackclient
RUN yum install -y --enablerepo=cci7-openstack-clients-qa python2-magnumclient
