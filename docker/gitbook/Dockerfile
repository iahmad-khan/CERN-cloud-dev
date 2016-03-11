# Based on dockerhub fellah/gitbook
#
# We keep our own in docker.cern.ch/cloud-infrastructure, just in case.

FROM node:latest

MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

LABEL version="2.6.6"

RUN npm install -g gitbook-cli && gitbook versions:install 2.6.6 && \
	apt-get clean autoclean && npm cache clear && \
	rm -rf /tmp/* /var/lib/{apt,dpkg,cache,log}/*

WORKDIR /srv/gitbook

VOLUME /srv/gitbook /srv/html

EXPOSE 4000 35729

CMD /usr/local/bin/gitbook serve
