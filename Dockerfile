FROM openjdk:17-jdk
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=jobradarbot_bot
ENV BOT_TOKEN=7451273044:AAFVjFUzJBCVMFLFkI---vlCFMTC52rCZHY
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]