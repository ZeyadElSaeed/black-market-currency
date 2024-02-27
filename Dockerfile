# Use an OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
ARG JAR_FILE=target/*.jar

# Copy the packaged Spring Boot application JAR file into the container
COPY ./target/black-market-currency-0.0.1-SNAPSHOT.jar app.jar
# Expose the port your application runs on
EXPOSE 8080
# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
