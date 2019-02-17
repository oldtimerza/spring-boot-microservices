FROM openjdk:8-jre-alpine

ARG JAR_FILE
ADD target/${JAR_FILE} /springboot-microservice-todo.jar

ENTRYPOINT ["java", "-jar","/springboot-microservice-todo.jar"]
