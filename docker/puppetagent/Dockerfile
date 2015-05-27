FROM rochaporto/puppetbase:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN yum install puppet -y && \
    yum clean all

ENTRYPOINT [ "/usr/bin/puppet", "agent", "-t", "--verbose" ]

