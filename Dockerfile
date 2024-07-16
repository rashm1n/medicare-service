# First stage: Build the native image
FROM ghcr.io/graalvm/graalvm-ce:latest as builder

# Install Maven
RUN microdnf install maven

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Install GraalVM Native Image component
RUN gu install native-image

# Use a local Maven repository cache
RUN mvn dependency:go-offline

# Build the native image
RUN mvn -Pnative native:compile

# Second stage: Create the runtime image
FROM debian:buster-slim

# Set the working directory
WORKDIR /app
RUN addgroup -g 10014 medicare && \
    adduser  --disabled-password  --no-create-home --uid 10014 --ingroup medicare medicareuser

# Copy the native executable from the builder stage
COPY --from=builder /app/target/medicare .

# Set the executable bit (if needed)
RUN chmod +x medicare
USER 10014
# Define the entry point for the container
ENTRYPOINT ["./medicare"]

# Expose any ports your application uses (if needed)
EXPOSE 8080
