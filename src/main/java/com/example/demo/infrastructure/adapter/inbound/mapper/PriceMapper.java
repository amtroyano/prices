package com.example.demo.infrastructure.adapter.inbound.mapper;

import com.example.demo.domain.model.Price;
import com.example.demo.infrastructure.adapter.inbound.dto.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  PriceResponse toResponse(Price price);
}
