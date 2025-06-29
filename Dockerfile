FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build/libs/telegram-devops-bot-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]