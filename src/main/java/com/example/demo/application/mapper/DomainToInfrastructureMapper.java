package com.example.demo.application.mapper;

import com.example.demo.domain.model.FilterPrice;
import com.example.demo.domain.model.request.FilterPriceRequest;
import com.example.demo.domain.model.response.PriceResponse;
import com.example.demo.infrastructure.adapter.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DomainToInfrastructureMapper {

  FilterPrice toDomain(FilterPriceRequest filterPriceRequest);

  @Mapping(source = "amount.price", target = "finalPrice")
  @Mapping(source = "amount.currency", target = "currency")
  PriceResponse toResponse(PriceEntity priceEntity);
}
