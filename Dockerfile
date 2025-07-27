# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Copy your JAR file into the container
COPY target/demo-afsd-two-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
