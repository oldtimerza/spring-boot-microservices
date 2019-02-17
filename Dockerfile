FROM openjdk:8-jre-alpine

ARG JAR_FILE
ADD target/${JAR_FILE} /springboot-microservice-todo.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar","/springboot-microservice-todo.jar"]
