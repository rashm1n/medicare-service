FROM ghcr.io/graalvm/graalvm-ce:ol7-java17-22.3.3 as builder
RUN yum install -y wget && \
    wget https://archive.apache.org/dist/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz && \
    tar xzf apache-maven-3.9.4-bin.tar.gz && \
    mv apache-maven-3.9.4 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -Pnative clean package

FROM oraclelinux:9-slim
WORKDIR /app
RUN groupadd -g 10014 medicare && \
    useradd -r -u 10014 -g medicare medicareuser
COPY --from=builder /app/target/medicare .
RUN chmod +x medicare
USER 10014
EXPOSE 8080
CMD ["./medicare"]