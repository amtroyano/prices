package com.example.demo.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public record MoneyEntity(
    @NotNull @Column(name = "PRICE", nullable = false) Double price,
    @NotNull @Size(min = 3, max = 3) @Column(name = "CURR", nullable = false) String currency) {}
