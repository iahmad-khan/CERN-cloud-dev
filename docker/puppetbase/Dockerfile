FROM rochaporto/base:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN rpm --import https://yum.puppetlabs.com/RPM-GPG-KEY-puppetlabs && \
    rpm -ivh https://yum.puppetlabs.com/puppetlabs-release-el-7.noarch.rpm

RUN yum install yum-utils -y && \
    yum clean all
    
ADD puppet.conf /etc/puppet/puppet.conf

