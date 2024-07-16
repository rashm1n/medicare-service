# First stage: Build the native image
FROM ghcr.io/graalvm/native-image:latest as builder

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Install Maven dependencies and build the native image
RUN gu install native-image
RUN mvn -Pnative native:compile

# Second stage: Create the runtime image
FROM debian:buster-slim

# Set the working directory
WORKDIR /app

# Copy the native executable from the builder stage
COPY --from=builder /app/target/medicare .

# Set the executable bit (if needed)
RUN chmod +x medicare

# Define the entry point for the container
ENTRYPOINT ["./medicare"]

# Expose any ports your application uses (if needed)
EXPOSE 8080
