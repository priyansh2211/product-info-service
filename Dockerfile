FROM maven:3.6.3-openjdk-11-slim as builder

WORKDIR /app
COPY pom.xml .
# Use this optimization to cache the local dependencies. Works as long as the POM doesn't change
RUN mvn dependency:go-offline

COPY src/ /app/src/
RUN mvn install package

# Use AdoptOpenJDK for base image.
FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/*.jar /app.jar

WORKDIR /app
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080