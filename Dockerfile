# Use the official GraalVM image as the base image for native build
FROM ghcr.io/graalvm/graalvm-ce:ol7-java17-22.3.3 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY mvnw .
RUN ./mvnw package -Pnative -DskipTests

FROM busybox:glibc
WORKDIR /app
RUN addgroup --gid 10014 medicare && \
    adduser --disabled-password --no-create-home --uid 10014 --ingroup medicare medicareuser
COPY --from=builder /app/target/medicare .
RUN chmod +x medicare
USER 10014
EXPOSE 8080
ENTRYPOINT ["./medicare"]
