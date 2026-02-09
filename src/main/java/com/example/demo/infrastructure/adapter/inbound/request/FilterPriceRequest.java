package com.example.demo.infrastructure.adapter.inbound.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public record FilterPriceRequest(
    @Schema(example = "35455", description = "Identificador del producto") @NotNull @Min(1)
        Long productId,
    @Schema(example = "1", description = "Identificador de la marca") @NotNull @Min(1)
        Integer brandId,
    @Schema(example = "2020-11-30T23:59:59", description = "Fecha de aplicación (ISO 8601)")
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime dateToSearch) {}
