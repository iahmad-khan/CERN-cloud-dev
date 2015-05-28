FROM centos:centos7
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN mkdir -p /etc/yum-puppet.repos.d && \
    curl -s http://linux.web.cern.ch/linux/centos7/CentOS-CERN.repo -o /etc/yum-puppet.repos.d/CentOS-CERN.repo && \
    { rm /etc/yum.conf && awk '/\[main\]/ { print; print "reposdir=/etc/yum-puppet.repos.d,/etc/yum.repos.d"; next }1' > /etc/yum.conf; } < /etc/yum.conf

RUN yum install -y \
    hostname \
    vim \
    && yum clean all
