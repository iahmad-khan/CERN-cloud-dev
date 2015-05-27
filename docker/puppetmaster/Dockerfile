FROM rochaporto/puppetbase:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN yum install puppet-server -y && \
    yum clean all

RUN echo '*.kubdomain.local' > /etc/puppet/autosign.conf

EXPOSE 8140

ENTRYPOINT [ "/usr/bin/puppet", "master", "--no-daemonize", "--verbose" ]

