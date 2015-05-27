FROM rochaporto/puppetbase:latest
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN yum install puppetdb -y && \
    yum clean all

CMD /usr/bin/sleep 3 && \
    /usr/sbin/puppetdb ssl-setup

ENTRYPOINT [ "/usr/sbin/puppetdb", "foreground" ]
