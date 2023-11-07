FROM ubuntu:latest
LABEL authors="victor.wanderley"
WORKDIR /app
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]