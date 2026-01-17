# Multi-stage Dockerfile for building all microservices

# Stage 1: Build common module
FROM maven:3.9-eclipse-temurin-17 AS common-builder
WORKDIR /app
COPY pom.xml .
COPY jari-common/pom.xml ./jari-common/
COPY jari-common/src ./jari-common/src
RUN mvn clean install -pl jari-common -am -DskipTests

# Stage 2: Build discovery service
FROM maven:3.9-eclipse-temurin-17 AS discovery
WORKDIR /app
COPY --from=common-builder /app/jari-common/target ./jari-common/target
COPY pom.xml .
COPY jari-discovery/pom.xml ./jari-discovery/
COPY jari-discovery/src ./jari-discovery/src
RUN mvn clean package -pl jari-discovery -am -DskipTests
ENTRYPOINT ["java", "-jar", "jari-discovery/target/jari-discovery-0.0.1-SNAPSHOT.jar"]

# Stage 3: Build gateway
FROM maven:3.9-eclipse-temurin-17 AS gateway
WORKDIR /app
COPY --from=common-builder /app/jari-common/target ./jari-common/target
COPY pom.xml .
COPY jari-gateway/pom.xml ./jari-gateway/
COPY jari-gateway/src ./jari-gateway/src
RUN mvn clean package -pl jari-gateway -am -DskipTests
ENTRYPOINT ["java", "-jar", "jari-gateway/target/jari-gateway-0.0.1-SNAPSHOT.jar"]

# Stage 4: Build user-service
FROM maven:3.9-eclipse-temurin-17 AS user-service
WORKDIR /app
COPY --from=common-builder /app/jari-common/target ./jari-common/target
COPY pom.xml .
COPY jari-user-service/pom.xml ./jari-user-service/
COPY jari-user-service/src ./jari-user-service/src
RUN mvn clean package -pl jari-user-service -am -DskipTests
ENTRYPOINT ["java", "-jar", "jari-user-service/target/jari-user-service-0.0.1-SNAPSHOT.jar"]

# Stage 5: Build project-service
FROM maven:3.9-eclipse-temurin-17 AS project-service
WORKDIR /app
COPY --from=common-builder /app/jari-common/target ./jari-common/target
COPY pom.xml .
COPY jari-project-service/pom.xml ./jari-project-service/
COPY jari-project-service/src ./jari-project-service/src
RUN mvn clean package -pl jari-project-service -am -DskipTests
ENTRYPOINT ["java", "-jar", "jari-project-service/target/jari-project-service-0.0.1-SNAPSHOT.jar"]

# Stage 6: Build task-service
FROM maven:3.9-eclipse-temurin-17 AS task-service
WORKDIR /app
COPY --from=common-builder /app/jari-common/target ./jari-common/target
COPY pom.xml .
COPY jari-task-service/pom.xml ./jari-task-service/
COPY jari-task-service/src ./jari-task-service/src
RUN mvn clean package -pl jari-task-service -am -DskipTests
ENTRYPOINT ["java", "-jar", "jari-task-service/target/jari-task-service-0.0.1-SNAPSHOT.jar"]

# Stage 7: Build notification-service
FROM maven:3.9-eclipse-temurin-17 AS notification-service
WORKDIR /app
COPY --from=common-builder /app/jari-common/target ./jari-common/target
COPY pom.xml .
COPY jari-notification-service/pom.xml ./jari-notification-service/
COPY jari-notification-service/src ./jari-notification-service/src
RUN mvn clean package -pl jari-notification-service -am -DskipTests
ENTRYPOINT ["java", "-jar", "jari-notification-service/target/jari-notification-service-0.0.1-SNAPSHOT.jar"]
