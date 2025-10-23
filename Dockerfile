# Step 1: Use lightweight Java 17 base image
FROM openjdk:17-jdk-slim

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy your built JAR into the container
COPY target/*.jar app.jar

# Step 4: Expose the default Spring Boot port
EXPOSE 8080

# Step 5: Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
