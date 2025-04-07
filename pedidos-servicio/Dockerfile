# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copiamos TODO el proyecto desde el contexto raíz (libreria-app)
COPY . .

RUN mvn clean package -DskipTests

# Etapa 2: Imagen de producción con Eclipse Temurin y OpenJDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copia el jar compilado desde la etapa de construcción
COPY --from=builder /app/pedidos-servicio/target/pedidos-servicio-1.0.0.jar app.jar
# Expone el puerto configurado (por ejemplo, 8083)
EXPOSE 8083
# Comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
