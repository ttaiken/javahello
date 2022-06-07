FROM openjdk:17.0.2-slim-buster
COPY ./app/build/libs/app.jar /app.jar

ENTRYPOINT ["java",  "-jar", "/app.jar"]