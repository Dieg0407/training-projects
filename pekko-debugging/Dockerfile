# Stage 1: Build the application
FROM gradle:jdk25 AS builder
WORKDIR /app

# Copy the Gradle configuration and source code
COPY build.gradle settings.gradle ./
COPY src ./src

# Build the Spring Boot fat JAR without the Gradle daemon to save memory
RUN gradle bootJar --no-daemon

# Stage 2: Create the runtime image using a plain JDK 25
FROM eclipse-temurin:25-jdk
WORKDIR /app

# Copy the generated JAR from the builder stage
# (Spring Boot typically generates the fat JAR in build/libs)
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the standard Spring Boot web port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]