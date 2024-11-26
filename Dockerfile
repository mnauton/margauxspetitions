FROM tomcat:latest
RUN mv /usr/local/tomcat/webapps /usr/local/tomcat/webapps2
RUN mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps

# Copy the WAR file
ADD target/*.war /usr/local/tomcat/webapps/

EXPOSE 8081

# Start Tomcat
CMD ["catalina.sh", "run"]
