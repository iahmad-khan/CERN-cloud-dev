FROM rochaporto/puppetbase:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN yum install -y \
    puppet-server \
    puppetdb-terminus \
    ruby-ldap \
    && yum clean all

RUN echo '*.kubdomain.local' > /etc/puppet/autosign.conf

EXPOSE 8140

ENTRYPOINT [ "/usr/bin/puppet", "master", "--no-daemonize", "--verbose" ]

