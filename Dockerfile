FROM openjdk:17
LABEL authors="victor.wanderley"
WORKDIR /app
COPY /build/libs/PunchGuardian-1.4.0.jar PunchGuardian.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","PunchGuardian.jar"]