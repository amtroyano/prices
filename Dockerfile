FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar el pom y descargar dependencias (para aprovechar la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y compilar el jar
COPY src ./src
RUN mvn clean package

# Fase 2: Run (Ejecución)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear un usuario no root por seguridad (Best Practice)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el JAR generado desde la fase de compilación
COPY --from=build app/target/*.jar app.jar

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8080

# Configurar el arranque
ENTRYPOINT ["java", "-jar", "app.jar"]