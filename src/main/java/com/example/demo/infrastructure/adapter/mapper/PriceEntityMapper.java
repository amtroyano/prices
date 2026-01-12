package com.example.demo.infrastructure.adapter.mapper;

import com.example.demo.domain.model.Price;
import com.example.demo.infrastructure.adapter.outbound.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

  @Mapping(source = "price", target = "finalPrice")
  @Mapping(source = "curr", target = "currency")
  Price toDomain(PriceEntity entity);

  @Mapping(source = "finalPrice", target = "price")
  @Mapping(source = "currency", target = "curr")
  PriceEntity toEntity(Price domain);
}
