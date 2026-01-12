# Pricing Service

Este proyecto es una implementación de una API REST para la consulta de tarifas de precios de productos, desarrollada
bajo los estándares de **Arquitectura Hexagonal** y **Clean Code** solicitados por Inditex.

## 🚀 Tecnologías y Herramientas

* **Java 21**
* **Spring Boot 3.x**
* **Spring Data JPA** con base de datos **H2** en memoria.
* **MapStruct**: Para el mapeo de objetos entre capas (Dominio/Dbo/Dto).
* **Lombok**: Para reducir el código repetitivo.
* **SpringDoc OpenAPI 3 (Swagger UI)**: Para documentación y pruebas de la API.
* **JUnit 5 & MockMvc**: Para la estrategia de tests unitarios e integrales.
* **Docker**: Containerización del servicio.

## 🏗️ Arquitectura: Hexagonal (Ports & Adapters)

El proyecto está estructurado para aislar la lógica de negocio de las dependencias externas:

- **`domain`**: Contiene el modelo de negocio (`Price`) y las excepciones de dominio. No tiene dependencias de
    - **Exceptions**: Las excepciones del negocio.
    - **model**: Los objetos que representan el dominio.
      frameworks.
- **`application`**: Define los puertos de entrada (`GetPriceUseCase`) y salida (`PriceRepositoryPort`), además de la
  implementación del servicio.
    - **ports**: Puertos de en trada y salida
    - **usecase**: Los casos de uso de la aplicación.
- **`infrastructure`**:
    - **Adapters Inbound**: Controlador REST, DTOs de respuesta y validaciones.
    - **Adapters Outbound**: Repositorio JPA, Entidades de base de datos y Mappers.
    - **Configuration**: Gestión global de excepciones (`@RestControllerAdvice`) y configuración de OpenAPI.

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

### Opción 2: Ejecución con Docker (Recomendado)

Para levantar el servicio en un contenedor aislado:

```bash
docker build -t pricing .
docker run -p 8080:8080 pricing
```

### Tests

En la carpeta .postman en la raíz del proyecto, se encuentra la colección postman para hacer pruebas.