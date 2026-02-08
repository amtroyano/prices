package com.example.demo.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "Información del precio final aplicable")
public record PriceResponse(
    @Schema(example = "1", description = "Identificador del producto") Long productId,
    @Schema(example = "1", description = "Identificador de la marca") Integer brandId,
    @Schema(example = "3", description = "Tarifa de precios aplicable") Integer priceList,
    @Schema(example = "2020-06-14-00.00.00", description = "Fecha de inicio del precio")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime startDate,
    @Schema(example = "2020-06-14-00.00.00", description = "Fecha de fin del precio")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime endDate,
    @Schema(example = "25.25", description = "Precio entre las fechas marcadas")
        BigDecimal finalPrice,
    @Schema(example = "EUR", description = "Moneda") String currency) {}
