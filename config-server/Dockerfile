# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copiamos TODO el proyecto desde el contexto raíz (libreria-app)
COPY . .

RUN mvn clean package -DskipTests

# Etapa 2: Producción
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /app/config-server/target/config-server-1.0.0.jar app.jar

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]

