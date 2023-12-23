# Estágio de construção
FROM gradle:jdk17 as builder
LABEL authors="victor.wanderley"
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN gradle build --no-daemon --exclude-task test

# Estágio de execução
FROM openjdk:17
LABEL authors="victor.wanderley"
WORKDIR /app
COPY --from=builder /app/build/libs/PunchGuardian-1.4.1-snapshot.jar PunchGuardian.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "PunchGuardian.jar"]
