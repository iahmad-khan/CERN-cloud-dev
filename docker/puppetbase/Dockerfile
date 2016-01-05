FROM docker.cern.ch/cloud-infrastructure/base:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN mkdir -p /etc/yum-puppet.repos.d && \
    rpm --import https://yum.puppetlabs.com/RPM-GPG-KEY-puppetlabs && \
    rpm -ivh https://yum.puppetlabs.com/puppetlabs-release-el-7.noarch.rpm && \
    rpm --import http://download.fedoraproject.org/pub/epel/RPM-GPG-KEY-EPEL-7 && \
    rpm -ivh http://download.fedoraproject.org/pub/epel/7/x86_64/e/epel-release-7-5.noarch.rpm && \
    rpm --import http://linuxsoft.cern.ch/cern/centos/7/os/x86_64/RPM-GPG-KEY-cern && \
    mv /etc/yum.repos.d/* /etc/yum-puppet.repos.d && \
    curl -s http://linux.web.cern.ch/linux/centos7/CentOS-CERN.repo -o /etc/yum-puppet.repos.d/CentOS-CERN.repo && \
    echo "reposdir=/etc/yum-puppet.repos.d" >> /etc/yum.conf

RUN yum install -y \
        make \
        patch \
        yum-utils && \
    yum clean all

