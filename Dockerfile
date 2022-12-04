FROM openjdk:17
COPY target/consumer-webapp.jar consumer-webapp.jar
ENTRYPOINT ["java", "-jar", "consumer-webapp.jar"]

EXPOSE 8081/tcp
