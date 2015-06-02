FROM rochaporto/base:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN rpm --import https://yum.puppetlabs.com/RPM-GPG-KEY-puppetlabs && \
    rpm -ivh https://yum.puppetlabs.com/puppetlabs-release-el-7.noarch.rpm

RUN yum install yum-utils -y && \
    yum clean all

RUN mkdir -p /etc/yum-puppet.repos.d && \
    curl -s http://linux.web.cern.ch/linux/centos7/CentOS-CERN.repo -o /etc/yum-puppet.repos.d/CentOS-CERN.repo && \
    echo "reposdir=/etc/yum-puppet.repos.d,/etc/yum.repos.d" >> /etc/yum.conf && \
    cat /etc/yum.conf
