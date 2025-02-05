FROM openjdk:17-jdk-slim

WORKDIR /app

COPY ../target/Eventrra.jar /app/eventrra.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "eventrra.jar"]