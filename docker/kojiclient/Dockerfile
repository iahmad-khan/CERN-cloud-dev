FROM centos:7

MAINTAINER Ricardo Rocha, ricardo.rocha@cern.ch

RUN yum install -y \
        man-pages \
	koji \
	rpm-build \
	rpmdevtools \
	krb5-workstation

ADD krb5.conf /etc/krb5.conf

ADD koji.conf /etc/koji.conf

RUN rpmdev-setuptree

