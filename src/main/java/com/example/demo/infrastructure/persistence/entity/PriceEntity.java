package com.example.demo.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRICES")
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PriceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "BRAND_ID", nullable = false)
  private Integer brandId;

  @NotNull
  @Column(name = "START_DATE", nullable = false)
  private OffsetDateTime startDate;

  @NotNull
  @Column(name = "END_DATE", nullable = false)
  private OffsetDateTime endDate;

  @NotNull
  @Column(name = "PRICE_LIST", nullable = false)
  private Integer priceList;

  @NotNull
  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @NotNull
  @Column(name = "PRIORITY", nullable = false)
  private Integer priority;

  @Embedded private MoneyEntity amount;
}
