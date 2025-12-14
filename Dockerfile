# Use a supported Java 21 image from Eclipse Temurin
FROM eclipse-temurin:21-jdk-jammy

# Set working directory inside the container
WORKDIR /app

# Copy the generated jar file into the container
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
