package com.example.demo.application.mapper;

import com.example.demo.domain.model.FilterPrice;
// import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
// import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;
import com.example.demo.infrastructure.adapter.dto.FilterPriceRequest;
import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import com.example.demo.infrastructure.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DomainToInfrastructureMapper {

  FilterPrice toDomain(FilterPriceRequest filterPriceRequest);

  @Mapping(source = "amount.price", target = "finalPrice")
  @Mapping(source = "amount.currency", target = "currency")
  PriceResponse toResponse(PriceEntity priceEntity);
}
