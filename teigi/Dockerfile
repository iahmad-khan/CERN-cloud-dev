FROM centos:centos7
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

ADD teigi /teigi

ENTRYPOINT ["/teigi"]

CMD ["-privcert", "/ssl/private_keys/puppet.default.kubdomain.local.pem", "-pubcert", "/ssl/certs/puppet.default.kubdomain.local.pem", "-cacert", "/ssl/certs/ca.pem", "-sleep", "5"]
