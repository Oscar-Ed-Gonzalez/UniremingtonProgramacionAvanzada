# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

COPY . .
#RUN mvn dependency:go-offline -B

#COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Producción
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /app/eureka-server/target/eureka-server-1.0.0.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
