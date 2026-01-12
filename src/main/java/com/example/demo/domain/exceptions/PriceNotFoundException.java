package com.example.demo.domain.exceptions;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
  public PriceNotFoundException(Integer brandId, Long productId, LocalDateTime date) {
    super(
        String.format(
            "No price found for brand %d, product %d at date %s", brandId, productId, date));
  }
}
