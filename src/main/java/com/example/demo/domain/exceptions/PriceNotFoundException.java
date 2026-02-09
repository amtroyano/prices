package com.example.demo.domain.exceptions;

import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;

public class PriceNotFoundException extends RuntimeException {
  public PriceNotFoundException(FilterPriceRequest filterPriceRequest) {
    super(
        String.format(
            "No price found for brand %d, product %d at date %s",
            filterPriceRequest.brandId(),
            filterPriceRequest.productId(),
            filterPriceRequest.dateToSearch()));
  }
}
