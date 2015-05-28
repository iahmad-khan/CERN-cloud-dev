FROM rochaporto/puppetbase:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN rpm --import http://download.fedoraproject.org/pub/epel/RPM-GPG-KEY-EPEL-7 && \
    rpm -ivh http://mirror.de.leaseweb.net/epel/7/x86_64/e/epel-release-7-5.noarch.rpm

RUN yum install -y \
    puppet-server \
    puppetdb-terminus \
    ruby-ldap \
    && yum clean all

RUN echo '*.kubdomain.local' > /etc/puppet/autosign.conf

EXPOSE 8140

ENTRYPOINT [ "/usr/bin/puppet", "master", "--no-daemonize", "--verbose" ]

