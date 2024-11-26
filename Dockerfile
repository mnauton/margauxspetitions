FROM tomcat:latest

# Copy the WAR file
ADD target/*.war /usr/local/tomcat/webapps/

# Update the Tomcat configuration to use different ports
RUN sed -i 's/port="8080"/port="9090"/g' /usr/local/tomcat/conf/server.xml

# Expose the new port
EXPOSE 9090

# Start Tomcat
CMD ["catalina.sh", "run"]
