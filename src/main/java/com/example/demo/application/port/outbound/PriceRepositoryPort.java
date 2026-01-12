package com.example.demo.application.port.outbound;

import com.example.demo.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
  Optional<Price> findApplicablePrice(Integer brandId, Long productId, LocalDateTime date);
}
