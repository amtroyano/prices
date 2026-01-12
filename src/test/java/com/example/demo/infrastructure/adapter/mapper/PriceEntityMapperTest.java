package com.example.demo.infrastructure.adapter.mapper;

import com.example.demo.domain.model.Price;
import com.example.demo.infrastructure.adapter.outbound.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceEntityMapperTest {

  @Autowired private PriceEntityMapper mapper;

  @Test
  void shouldMapEntityToDomain() {
    PriceEntity entity = new PriceEntity();
    entity.setPrice(25.45);
    entity.setCurr("EUR");
    entity.setProductId(35455L);

    Price domain = mapper.toDomain(entity);

    assertEquals(entity.getPrice(), domain.getFinalPrice());
    assertEquals(entity.getCurr(), domain.getCurrency());
    assertEquals(entity.getProductId(), domain.getProductId());
  }
}
