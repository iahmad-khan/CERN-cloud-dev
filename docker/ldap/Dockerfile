## Based on the work by Manuel Vacelet, manuel.vacelet@enalean.com
#
# Example: 
# sudo docker run -v `pwd`:/ldif -p 389:389 -e LDAP_ROOT_PASSWORD=123456 -e LDAP_MANAGER_PASSWORD=123456 gitlab-registry.cern.ch/cloud/ldap
#
# You can mount /ldif with ldif files and it will load the data on start.
#

FROM centos:centos6

MAINTAINER Ricardo Rocha, ricardo.rocha@cern.ch

ENV DEBUG_LEVEL=256
EXPOSE 389 636
VOLUME [ "/data", "/ldif" ]

RUN yum -y update && \
    yum -y install openldap-servers openldap-clients && \
    yum clean all

COPY . /root

CMD ["/root/run.sh"]
