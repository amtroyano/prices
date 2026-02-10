package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record Price(
    Long productId,
    Integer brandId,
    Integer priceList,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    BigDecimal finalPrice,
    String currency) {}
