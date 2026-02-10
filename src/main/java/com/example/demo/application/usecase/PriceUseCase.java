package com.example.demo.application.usecase;

import com.example.demo.application.port.GetPriceUseCase;
import com.example.demo.domain.exceptions.PriceNotFoundException;
import com.example.demo.domain.model.FilterPrice;
import com.example.demo.domain.model.Price;
import com.example.demo.domain.port.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceUseCase implements GetPriceUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  @Override
  public Price execute(final FilterPrice filterPrice) {

    return priceRepositoryPort
        .findTopPrice(filterPrice)
        .orElseThrow(() -> new PriceNotFoundException(filterPrice));
  }
}
