package com.example.demo.infrastructure.adapter.inbound;

import com.example.demo.application.port.inbound.GetPriceUseCase;
import com.example.demo.domain.model.Price;
import com.example.demo.infrastructure.adapter.inbound.dto.PriceResponse;
import com.example.demo.infrastructure.adapter.inbound.mapper.PriceMapper;
import com.example.demo.infrastructure.port.PriceControllerApi;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
@Validated

public class PriceController implements PriceControllerApi {

  private final GetPriceUseCase getPriceUseCase;
  private final PriceMapper priceMapper;

  @GetMapping
  public ResponseEntity<PriceResponse> getPrice(
      Integer brandId, Long productId, LocalDateTime applicationDate) {

    Price price = getPriceUseCase.execute(brandId, productId, applicationDate);
    return ResponseEntity.ok(priceMapper.toResponse(price));
  }
}
