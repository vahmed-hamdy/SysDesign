FROM openjdk:11-jdk
MAINTAINER baeldung.com
COPY target/SysDesign-0.0.1-SNAPSHOT.jar dummy-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/dummy-server-1.0.0.jar"]