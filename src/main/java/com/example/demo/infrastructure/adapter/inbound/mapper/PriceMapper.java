package com.example.demo.infrastructure.adapter.inbound.mapper;

import com.example.demo.domain.model.FilterPrice;
import com.example.demo.domain.model.Price;
import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import java.time.OffsetDateTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  PriceResponse toDomain(Price price);

  FilterPrice toDomain(Long productId, Integer brandId, OffsetDateTime dateToSearch);
}
