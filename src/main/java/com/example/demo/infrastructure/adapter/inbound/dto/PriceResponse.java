package com.example.demo.infrastructure.adapter.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información del precio final aplicable")
public class PriceResponse {

  @Schema(example = "35455")
  private Long productId;

  @Schema(example = "1")
  private Integer brandId;

  @Schema(example = "1", description = "Tarifa de precios aplicable")
  private Integer priceList;

  @Schema(example = "2020-06-14-00.00.00")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime startDate;

  @Schema(example = "2020-06-14-00.00.00")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime endDate;

  @Schema(example = "2020-06-14-00.00.00")
  private Double finalPrice;

  @Schema(example = "2020-06-14-00.00.00")
  private String currency;
}
