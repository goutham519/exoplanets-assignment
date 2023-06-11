FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the target classes to the container
COPY ./target/classes/ /app

# Set the entry point command for the container
ENTRYPOINT ["java", "-cp", "com.exoplanet", "com.exoplanet.ExoplanetApplication"]

