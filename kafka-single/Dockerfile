#
# Build stage
#
FROM maven:3.8.3-openjdk-17  AS build
COPY src ./src
COPY pom.xml .
RUN mvn -f ./pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:17
WORKDIR /kafka
MAINTAINER djain
EXPOSE 5353
COPY --from=build ./target/kafka-app.jar /usr/local/lib/kafka-app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dock /usr/local/lib/kafka-app.jar"]