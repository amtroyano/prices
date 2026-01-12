package com.example.demo.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Price {
  private Long productId;
  private Integer brandId;
  private Integer priceList;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Double finalPrice;
  private String currency;
}
