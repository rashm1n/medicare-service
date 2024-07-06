FROM maven:3.8.5-openjdk-8-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-alpine
WORKDIR /app
RUN addgroup -g 10014 medicare && \
    adduser  --disabled-password  --no-create-home --uid 10014 --ingroup medicare medicareuser
COPY --from=build /app/target/medicare-1.0.0.jar app.jar
USER 10014
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]