package com.example.demo.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;

@Schema(description = "Objeto de error")
@Builder
public record ErrorResponse(
    @Schema(example = "020-06-14-00.00.00", description = "Fecha del error")
        LocalDateTime timestamp,
    @Schema(example = "404", description = "Código del error de la respuesta") int status,
    @Schema(example = "Objeto no encontrado", description = "Mensaje del error") String message,
    @Schema(example = "/list/prices", description = "LLamada que produjo el error") String path) {}
