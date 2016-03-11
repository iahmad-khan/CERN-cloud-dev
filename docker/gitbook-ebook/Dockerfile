# Based on dockerhub fellah/gitbook-ebook
#
# We keep our own in docker.cern.ch/cloud-infrastructure, just in case.

FROM docker.cern.ch/cloud-infrastructure/gitbook

MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN apt-get update && \
	apt-get install -y calibre && \
	apt-get clean && \
	rm -rf /tmp/* /var/lib/{apt,dpkg,cache,log}/*

CMD /usr/local/bin/gitbook serve
