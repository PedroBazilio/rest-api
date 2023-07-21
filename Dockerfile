FROM openjdk:17-alpine

COPY target/rest-api-0.0.1-SNAPSHOT.jar app-1.0.0.jar

ENTRYPOINT [ "java", "-jar", "app-1.0.0.jar"]
