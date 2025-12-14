# Use a lightweight Java runtime
FROM openjdk:21-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the generated jar file into the container
# (Make sure to run 'mvn clean package' locally first if testing Docker locally,
# but Render will build it for us)
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
