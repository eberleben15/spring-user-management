# Use the official Gradle image to create a build artifact.
FROM gradle:7.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Use the latest OpenJDK 17 slim image for the runtime.
FROM openjdk:17-slim
COPY --from=build /home/gradle/src/build/libs/*.jar /app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/application.jar"]