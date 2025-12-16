# =========================
# BUILD STAGE
# =========================
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests

# =========================
# RUNTIME STAGE
# =========================
FROM amazoncorretto:21
WORKDIR /app

COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
