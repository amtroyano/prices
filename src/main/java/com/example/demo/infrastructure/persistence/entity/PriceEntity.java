package com.example.demo.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
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

  //  @NotNull
  //  @Column(name = "PRICE", nullable = false)
  //  private BigDecimal price;
  //
  //  @NotNull
  //  @Size(min = 3, max = 3)
  //  @Column(name = "CURR", nullable = false)
  //  private String currency;

  @Embedded private MoneyEntity amount;
}
