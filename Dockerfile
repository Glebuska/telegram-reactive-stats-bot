FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/Glebuska/telegram-reactive-stats-bot telegram-app
RUN git checkout develop

FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY --from=clone /app/telegram-app /app
RUN mvn install

FROM amazoncorretto:11-alpine-jdk
COPY storage/target/storage-1.0-SNAPSHOT.jar storage-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/storage-1.0-SNAPSHOT.jar"]