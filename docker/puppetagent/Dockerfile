FROM docker.cern.ch/cloud-infrastructure/puppetbase:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN yum install puppet sudo initscripts -y && \
    yum clean all

ENTRYPOINT [ "/usr/bin/puppet", "agent", "-t", "--verbose" ]

