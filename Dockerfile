# Stage 1: Build the application
FROM ghcr.io/graalvm/graalvm-ce:ol7-java17-22.3.3 as builder
RUN microdnf install -y maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -Pnative clean package


FROM oraclelinux:9-slim
WORKDIR /app
RUN addgroup --gid 10014 medicare && \
    adduser --disabled-password --no-create-home --uid 10014 --ingroup medicare medicareuser
COPY --from=builder /app/target/medicare ./medicare
USER 10014
EXPOSE 8080
CMD ["./medicare"]