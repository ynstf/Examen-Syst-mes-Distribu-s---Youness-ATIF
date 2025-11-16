# Use OpenJDK 21 base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the jar (after building with Maven)
COPY target/*.jar app.jar

# Expose service port (replace 9001 per service)
EXPOSE 9999

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
