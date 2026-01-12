package com.example.demo.application.port.inbound;

import com.example.demo.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
  Price execute(Integer brandId, Long productId, LocalDateTime date);
}
