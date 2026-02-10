package com.example.demo.domain.port;

import com.example.demo.domain.model.FilterPrice;
import com.example.demo.domain.model.Price;
import java.util.Optional;

public interface PriceRepositoryPort {
  Optional<Price> findTopPrice(FilterPrice filterPrice);
}
