package com.example.demo.domain.model;

import java.time.OffsetDateTime;

public record FilterPrice(Long productId, Integer brandId, OffsetDateTime dateToSearch) {}
