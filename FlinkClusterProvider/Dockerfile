FROM openjdk:11-jdk
COPY target/FlinkClusterProvider-0.0.1-SNAPSHOT.jar	 flink-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/flink-server-1.0.0.jar"]