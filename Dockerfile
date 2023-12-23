FROM openjdk:17
LABEL authors="victor.wanderley"
WORKDIR /app
COPY /build/libs/PunchGuardian.jar PunchGuardian.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "PunchGuardian.jar"]
