# Pricing Service

Este proyecto es una implementación de una API REST para la consulta de tarifas de precios de productos, desarrollada
bajo los estándares de **Arquitectura Hexagonal** y **Clean Code** solicitados por Inditex.

## 🚀 Tecnologías y Herramientas

* **Java 21**
* **Spring Boot 4.0.1**
* **Spring Data JPA** con base de datos **H2** en memoria.
* **MapStruct**: Para el mapeo de objetos entre capas (Dominio/Dbo/Dto).
* **Lombok**: Para reducir el código repetitivo.
* **SpringDoc OpenAPI 3 (Swagger UI)**: Para documentación y pruebas de la API.
* **JUnit 5 & MockMvc**: Para la estrategia de tests unitarios e integrales.
* **Docker**: Containerización del servicio.

## 🏗️ Arquitectura: Hexagonal (Ports & Adapters)

El proyecto está estructurado para aislar la lógica de negocio de las dependencias del framework:

- **`domain`**: Contiene el modelo de negocio y las excepciones de dominio.
- **`application`**: Define los casos de uso con entrada y salida de datos.
- **`infrastructure`** Todo Aquello relacionado con el framework que no pertenece ni a casos de uso ni a dominio.

## 🛠️ Instalación y Ejecución

### Requisitos previos

* Maven 3.8+
* JDK 21 o superior instalado localmente.

### Opción 1: Ejecución Local (Maven)

1. Compilar y pasar tests:
   ```bash
   ./mvnw clean install
   ```
2. Arrancar la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

### Opción 2: Ejecución con Docker

1. Compilar y pasar tests:
   ```bash
   ./mvnw clean install

2. Para levantar el servicio en un contenedor aislado:

```bash
docker build -t pricing .
docker run -p 8080:8080 pricing
```

### Opción 3: Ejecución con Docker (recomendada)

Con el docker levantado en el equipo.

1. Entrar dentro de la carpeta "./execution" y ejecutar:

```bash
docker-compose up -d
```

=============================================

En la carpeta .postman en la raíz del proyecto, se encuentra la colección postman para hacer pruebas.