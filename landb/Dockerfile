FROM tomcat:7-jre7
MAINTAINER "Ricardo Rocha <ricardo.rocha@cern.ch>"

ADD server.xml /usr/local/tomcat/conf/server.xml

ADD axis/webapps/axis /usr/local/tomcat/webapps/axis

RUN /usr/bin/keytool -genkey -alias tomcat -keyalg RSA -keypass 123456 -storepass 123456 -dname 'ou=cern'

EXPOSE 8443

EXPOSE 8480

CMD ["catalina.sh", "run"]
