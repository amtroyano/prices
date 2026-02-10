package com.example.demo.infrastructure.adapter.inbound;

import com.example.demo.application.port.GetPriceUseCase;
import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import com.example.demo.infrastructure.adapter.inbound.mapper.PriceMapper;
import com.example.demo.infrastructure.port.PricesApi;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

  private final GetPriceUseCase getPriceUseCase;

  private final PriceMapper priceMapper;

  @Override
  public ResponseEntity<PriceResponse> getPrice(
      Long productId, Integer brandId, OffsetDateTime dateToSearch) {

    return ResponseEntity.ok(
        priceMapper.toDomain(
            getPriceUseCase.execute(priceMapper.toDomain(productId, brandId, dateToSearch))));
  }
}
