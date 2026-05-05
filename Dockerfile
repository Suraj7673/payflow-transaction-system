# Use Java 17 base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build the app
RUN ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/*.jar"]