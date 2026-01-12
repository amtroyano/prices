package com.example.demo.infrastructure.adapter.outbound.repository;

import com.example.demo.application.port.outbound.PriceRepositoryPort;
import com.example.demo.domain.model.Price;
import com.example.demo.infrastructure.adapter.mapper.PriceEntityMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final PriceRepository springDataPriceRepository;
  private final PriceEntityMapper mapper;

  @Override
  public Optional<Price> findApplicablePrice(Integer brandId, Long productId, LocalDateTime date) {
    return springDataPriceRepository.findTopPrice(brandId, productId, date).map(mapper::toDomain);
  }
}
