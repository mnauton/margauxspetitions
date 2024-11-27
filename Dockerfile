FROM tomcat:latest

RUN mv /usr/local/tomcat/webapps /usr/local/tomcat/webapps2
RUN mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps

COPY target/*.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]