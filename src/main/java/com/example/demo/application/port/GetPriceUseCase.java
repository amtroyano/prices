package com.example.demo.application.port;

import com.example.demo.domain.model.FilterPrice;
import com.example.demo.domain.model.Price;

public interface GetPriceUseCase {
  Price execute(FilterPrice filterPrice);
}
