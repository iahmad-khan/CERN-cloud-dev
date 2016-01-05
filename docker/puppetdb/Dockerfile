FROM docker.cern.ch/cloud-infrastructure/puppetbase:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN yum install -y \
    puppetdb && \
    yum clean all

ENTRYPOINT [ "sh", "-c", "sleep 5; /usr/sbin/puppetdb ssl-setup || true; /usr/sbin/puppetdb foreground" ]
