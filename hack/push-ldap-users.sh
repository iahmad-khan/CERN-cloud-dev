#!/bin/sh
#
# helper script to push ldap users to our ldap container in the controller.
#
# ideally this would be done inside the container itself, but due to the way
# the osixia/ldap container is defined (and how the daemon is called) it wasn't
# trivial. to be revisited at some point to get rid of this.

CID=$(sudo docker ps | grep ldap | awk '{print $1}')
CIP=$(kubectl.sh get pod | grep controller | awk '{print $2}')
echo "Updating ldap users on container $CID and pod IP $CIP..."
sudo docker exec -it $CID /bin/cp /tmp/kube.ldif /etc/ldap/slapd.d/cn\=config/cn\=schema/cn\=\{14}kube.ldif
sudo docker exec -it $CID /usr/bin/killall slapd
sleep 2
ldapadd -h $CIP -w 123456 -D 'cn=admin,dc=default,dc=kubdomain,dc=local' -c -f ../docker/ldap/ldap.ldif
