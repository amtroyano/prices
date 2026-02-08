package com.example.demo.domain.model;

import java.time.LocalDateTime;

public record FilterPrice(Long productId, Integer brandId, LocalDateTime dateToSearch) {}
