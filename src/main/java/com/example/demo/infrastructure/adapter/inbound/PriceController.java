package com.example.demo.infrastructure.adapter.inbound;

import com.example.demo.application.port.inbound.GetPriceUseCase;
// import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
// import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;
import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import com.example.demo.infrastructure.port.PricesApi;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

  private final GetPriceUseCase getPriceUseCase;

  @Override
  public ResponseEntity<PriceResponse> getPrice(
      Long productId, Integer brandId, OffsetDateTime dateToSearch) {

    return ResponseEntity.ok(getPriceUseCase.execute(productId, brandId, dateToSearch));
  }
}
