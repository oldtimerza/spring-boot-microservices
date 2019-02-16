FROM openjdk:8-jre-alpine

WORKING_DIRECTORY
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]


