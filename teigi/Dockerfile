FROM centos:centos7
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

ADD teigi /teigi

ADD teigi-db.json /teigi-db.json

ENTRYPOINT ["/teigi"]

CMD ["-privcert", "/ssl/private_keys/puppet.default.kubdomain.local.pem", "-pubcert", "/ssl/certs/puppet.default.kubdomain.local.pem", "-cacert", "/ssl/certs/ca.pem", "-sleep", "5", "-db", "/teigi-db.json"]
