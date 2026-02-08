package com.example.demo.infrastructure.adapter.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.infrastructure.adapter.persistence.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
