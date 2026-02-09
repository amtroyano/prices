package com.example.demo.infrastructure.adapter.inbound;

import com.example.demo.application.port.inbound.GetPriceUseCase;
import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;
import com.example.demo.infrastructure.port.PriceControllerApi;
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

  @GetMapping
  public ResponseEntity<PriceResponse> getPrice(FilterPriceRequest filterPriceRequest) {

    return ResponseEntity.ok(getPriceUseCase.execute(filterPriceRequest));
  }
}
