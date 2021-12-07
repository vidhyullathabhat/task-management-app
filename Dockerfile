FROM openjdk:8-jdk-alpine
MAINTAINER vidhyu
EXPOSE 8080
COPY target/sample-docker-1.0-SNAPSHOT-spring-boot.jar sample-docker-1.0.0.jar
ENTRYPOINT ["java","-jar","/sample-docker-1.0.0.jar"]