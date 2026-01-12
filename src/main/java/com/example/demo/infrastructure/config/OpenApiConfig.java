package com.example.demo.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Pricing API")
                .version("1.0.0")
                .description(
                    "Servicio para la gestión de precios y tarifas de productos por fechas y prioridad."));
  }
}
