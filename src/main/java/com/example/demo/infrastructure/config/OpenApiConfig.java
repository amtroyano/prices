package com.example.demo.infrastructure.config;

import com.example.demo.application.mapper.DomainToInfrastructureMapper;
import com.example.demo.application.port.inbound.GetPriceUseCase;
import com.example.demo.application.usecase.PriceUseCase;
import com.example.demo.infrastructure.adapter.persistence.repository.PriceRepositoryPort;
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

  @Bean
  public GetPriceUseCase getPriceUseCase(
      DomainToInfrastructureMapper domainToInfrastructureMapper,
      PriceRepositoryPort priceRepositoryPort) {
    return new PriceUseCase(domainToInfrastructureMapper, priceRepositoryPort);
  }
}
