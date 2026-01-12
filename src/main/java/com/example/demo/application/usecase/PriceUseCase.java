package com.example.demo.application.usecase;

import com.example.demo.application.port.inbound.GetPriceUseCase;
import com.example.demo.application.port.outbound.PriceRepositoryPort;
import com.example.demo.domain.exceptions.PriceNotFoundException;
import com.example.demo.domain.model.Price;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceUseCase implements GetPriceUseCase {

  private final PriceRepositoryPort repositoryPort;

  @Override
  public Price execute(Integer brandId, Long productId, LocalDateTime date) {
    return repositoryPort
        .findApplicablePrice(brandId, productId, date)
        .orElseThrow(() -> new PriceNotFoundException(brandId, productId, date));
  }
}
