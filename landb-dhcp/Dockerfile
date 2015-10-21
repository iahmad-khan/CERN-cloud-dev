FROM centos:centos7
MAINTAINER "Ricardo Rocha <ricardo.rocha@cern.ch>"

ADD dep-repos.repo /etc/yum.repos.d/dep-repos.repo

RUN yum install -y python-landbclient

ADD landb-dhcp /landb-dhcp

EXPOSE 67 68

ENTRYPOINT ["/landb-dhcp"]

CMD ["-landb-host", "localhost", "-landb-port", "8443", "-listen-iface", "eth0", "-listen-ip", ""]
