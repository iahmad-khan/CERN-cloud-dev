FROM centos:centos7
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

ADD landb /landb

VOLUME ["/data"]

ENTRYPOINT ["/landb"]

CMD ["-privcert", "/ssl/private_keys/puppet.default.kubdomain.local.pem", "-pubcert", "/ssl/certs/puppet.default.kubdomain.local.pem", "-cacert", "/ssl/certs/ca.pem", "-port", "8043", "-sleep", "5", "-db", "/data/landb-db.json"]
