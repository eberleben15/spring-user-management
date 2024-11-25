FROM gradle:8.11-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Clear Gradle cache
RUN rm -rf /home/gradle/.gradle/caches
RUN ./gradlew build --no-daemon