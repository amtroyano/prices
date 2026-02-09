package com.example.demo.application.usecase;

import com.example.demo.application.mapper.DomainToInfrastructureMapper;
import com.example.demo.application.port.inbound.GetPriceUseCase;
import com.example.demo.domain.exceptions.PriceNotFoundException;
// import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
// import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;
import com.example.demo.infrastructure.adapter.dto.FilterPriceRequest;
import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import com.example.demo.infrastructure.annotation.UseCase;
import com.example.demo.infrastructure.persistence.repository.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PriceUseCase implements GetPriceUseCase {

  private final DomainToInfrastructureMapper domainToInfrastructureMapper;
  private final PriceRepositoryPort priceRepositoryPort;

  @Override
  public PriceResponse execute(FilterPriceRequest filterPriceRequest) {

    return domainToInfrastructureMapper.toResponse(
        priceRepositoryPort
            .findTopPrice(domainToInfrastructureMapper.toDomain(filterPriceRequest))
            .orElseThrow(() -> new PriceNotFoundException(filterPriceRequest)));
  }
}
