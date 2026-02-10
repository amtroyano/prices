package com.example.demo.domain.exceptions;

// import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
import com.example.demo.domain.model.FilterPrice;

public class PriceNotFoundException extends RuntimeException {
  public PriceNotFoundException(final FilterPrice filterPrice) {
    super(
        String.format(
            "No price found for brand %d, product %d at date %s",
            filterPrice.brandId(), filterPrice.productId(), filterPrice.dateToSearch()));
  }
}
