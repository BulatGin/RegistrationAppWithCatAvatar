FROM maven:3.5.4-jdk-8-alpine as build
WORKDIR /app
COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package

FROM openjdk:8-jre-alpine
COPY --from=build /app/target/cat-service-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]