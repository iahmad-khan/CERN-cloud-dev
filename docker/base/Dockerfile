FROM centos:7
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

ENV container docker

# we need this or agetty takes 100% cpu
RUN cd /usr/lib/systemd; for i in system/autovt@.service system/getty.target system/serial-getty@.service system/getty@.service system/multi-user.target.wants/getty.target system/console-getty.service system/container-getty@.service system-generators/systemd-getty-generator; do rm -f $i; done

RUN rm /etc/sysconfig/network-scripts/ifcfg-ens3

RUN yum install -y \
    hostname \
    less \
    vim \
    && yum clean all

CMD ["/usr/sbin/init"]
