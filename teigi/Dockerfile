FROM centos:centos7
MAINTAINER Ricardo Rocha <ricardo.rocha@cern.ch>

ADD teigi /teigi

VOLUME ["/data"]

ENTRYPOINT ["/teigi"]

CMD ["-privcert", "/ssl/private_keys/puppet.default.svc.cluster.local.pem", "-pubcert", "/ssl/certs/puppet.default.svc.cluster.local.pem", "-cacert", "/ssl/certs/ca.pem", "-sleep", "5", "-db", "/data/teigi-db.json", "-filepath", "/opt/teigi"]
