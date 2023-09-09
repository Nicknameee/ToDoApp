# Fetching latest version of Java
FROM openjdk:17

# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/source-jar-with-dependencies.jar /app/source.jar

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "source.jar"]