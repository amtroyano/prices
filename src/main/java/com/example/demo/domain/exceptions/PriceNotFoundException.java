package com.example.demo.domain.exceptions;

// import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
import com.example.demo.infrastructure.adapter.dto.FilterPriceRequest;

public class PriceNotFoundException extends RuntimeException {
  public PriceNotFoundException(FilterPriceRequest filterPriceRequest) {
    super(
        String.format(
            "No price found for brand %d, product %d at date %s",
            filterPriceRequest.getBrandId(),
            filterPriceRequest.getProductId(),
            filterPriceRequest.getDateToSearch()));
  }
}
