package com.example.demo.infrastructure.port;

import com.example.demo.infrastructure.adapter.inbound.dto.ErrorResponse;
import com.example.demo.infrastructure.adapter.inbound.dto.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Prices", description = "API para consulta de tarifas de precios de productos")
public interface PriceControllerApi {

  @Operation(
      summary = "Consultar precio aplicable",
      description =
          "Devuelve el precio con mayor prioridad para un producto, cadena y fecha específica.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Precio encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "No se encontró un precio aplicable",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Parámetros de entrada inválidos",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<PriceResponse> getPrice(
      @Parameter(description = "ID de la cadena (1 = ZARA)", example = "1")
          @RequestParam
          @NotNull
          @Min(1)
          Integer brandId,
      @Parameter(description = "ID del producto", example = "35455") @RequestParam @NotNull @Min(1)
          Long productId,
      @Parameter(description = "Fecha de aplicación (ISO 8601)", example = "2020-06-14T10:00:00")
          @RequestParam
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDateTime applicationDate);
}
