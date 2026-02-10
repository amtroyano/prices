package com.example.demo.infrastructure.adapter.persistence.mapper;

import com.example.demo.domain.model.Price;
import com.example.demo.infrastructure.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricePersistenceMapper {

  @Mapping(source = "amount.price", target = "finalPrice")
  @Mapping(source = "amount.currency", target = "currency")
  Price toDomain(PriceEntity priceEntity);
}
