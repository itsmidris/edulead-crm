# Build stage
FROM maven:3.9.11-eclipse-temurin-25 AS build

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw ./
RUN chmod +x mvnw

COPY src ./src
RUN ./mvnw -B clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:25-jre

WORKDIR /app

RUN useradd --system --uid 10001 appuser

COPY --from=build --chown=appuser:appuser /app/target/*.jar app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=50.0", "-jar", "/app/app.jar"]