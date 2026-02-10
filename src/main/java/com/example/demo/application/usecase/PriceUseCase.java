package com.example.demo.application.usecase;

import com.example.demo.application.mapper.DomainToInfrastructureMapper;
import com.example.demo.application.port.inbound.GetPriceUseCase;
import com.example.demo.domain.exceptions.PriceNotFoundException;
// import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
// import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;
import com.example.demo.domain.model.FilterPrice;
import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import com.example.demo.infrastructure.annotation.UseCase;
import com.example.demo.infrastructure.persistence.repository.PriceRepositoryPort;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PriceUseCase implements GetPriceUseCase {

  private final DomainToInfrastructureMapper domainToInfrastructureMapper;
  private final PriceRepositoryPort priceRepositoryPort;

  @Override
  public PriceResponse execute(Long productId, Integer brandId, OffsetDateTime dateToSearch) {

    FilterPrice filterPrice =
        domainToInfrastructureMapper.toDomain(productId, brandId, dateToSearch);

    return domainToInfrastructureMapper.toResponse(
        priceRepositoryPort
            .findTopPrice(filterPrice)
            .orElseThrow(() -> new PriceNotFoundException(filterPrice)));
  }
}
