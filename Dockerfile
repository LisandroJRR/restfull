# Etapa 1: Construcción (Usamos Maven con Java 17)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Usamos una imagen de Java muy ligera)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/RestFull-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto estándar
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL authors="lisan"

