package com.example.demo.infrastructure.adapter.persistence;

import com.example.demo.domain.model.FilterPrice;
import com.example.demo.domain.model.Price;
import com.example.demo.domain.port.PriceRepositoryPort;
import com.example.demo.infrastructure.adapter.persistence.mapper.PricePersistenceMapper;
import com.example.demo.infrastructure.persistence.repository.JpaPriceRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceRepositoryPort {

  private final JpaPriceRepository jpaPriceRepository;
  private final PricePersistenceMapper pricePersistenceMapper;

  @Override
  public Optional<Price> findTopPrice(FilterPrice filterPrice) {
    return jpaPriceRepository.findTopPrice(filterPrice).map(pricePersistenceMapper::toDomain);
  }
}
