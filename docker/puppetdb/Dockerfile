FROM rochaporto/puppetbase:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN yum install puppetdb -y && \
    yum clean all

ENTRYPOINT [ "sh", "-c", "sleep 5; /usr/sbin/puppetdb ssl-setup || true; echo 'Starting puppetdb...'; /usr/sbin/puppetdb foreground" ]
