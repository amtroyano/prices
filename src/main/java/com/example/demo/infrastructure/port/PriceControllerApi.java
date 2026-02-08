package com.example.demo.infrastructure.port;

import com.example.demo.domain.model.request.FilterPriceRequest;
import com.example.demo.domain.model.response.ErrorResponse;
import com.example.demo.domain.model.response.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

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
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Error Interno",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<PriceResponse> getPrice(@NotNull @Valid FilterPriceRequest filterPriceRequest);
}
