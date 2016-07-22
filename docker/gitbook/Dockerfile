# Based on dockerhub fellah/gitbook
#
# We keep our own in docker.cern.ch/cloud-infrastructure, just in case.

FROM node:6.3.0

MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

LABEL version=3.2.0

RUN npm install -g gitbook-cli && gitbook fetch ${VERSION} && \
	apt-get clean autoclean && npm cache clear && \
	rm -rf /tmp/* /var/lib/{apt,dpkg,cache,log}/*

WORKDIR /srv/gitbook

VOLUME /srv/gitbook /srv/html

EXPOSE 4000 35729

CMD /usr/local/bin/gitbook serve
