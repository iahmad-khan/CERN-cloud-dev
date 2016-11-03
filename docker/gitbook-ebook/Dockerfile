# Based on dockerhub fellah/gitbook-ebook
#
# We keep our own in gitlab-registry.cern.ch/cloud, just in case.

FROM gitlab-registry.cern.ch/cloud/gitbook

MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

RUN apt-get update && \
	apt-get install -y calibre && \
	apt-get clean && \
	rm -rf /tmp/* /var/lib/{apt,dpkg,cache,log}/*

CMD /usr/local/bin/gitbook serve
