# Stage 1: Build the JAR
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy the pom.xml and source
COPY pom.xml .
COPY src ./src

# Remove the nested frontend folder to prevent build bloat
RUN rm -rf src/gscores-frontend

# Build the application (skipping tests for speed)
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]