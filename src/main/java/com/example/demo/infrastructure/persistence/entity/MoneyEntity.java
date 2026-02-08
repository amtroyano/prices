package com.example.demo.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Embeddable
public record MoneyEntity(
    @NotNull
        @Digits(integer = 10, fraction = 2)
        @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
        BigDecimal price,
    @NotNull @Size(min = 3, max = 3) @Column(name = "CURR", nullable = false) String currency) {}
