package com.example.demo.infrastructure.adapter.inbound;

import com.example.demo.application.port.inbound.GetPriceUseCase;
// import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
// import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;
import com.example.demo.infrastructure.adapter.dto.FilterPriceRequest;
import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import com.example.demo.infrastructure.port.PricesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
// public class PriceController implements PriceControllerApi {
public class PriceController implements PricesApi {

  private final GetPriceUseCase getPriceUseCase;

  @Override
  @GetMapping
  //  public ResponseEntity<PriceResponse> getPrice(FilterPriceRequest filterPriceRequest) {
  public ResponseEntity<PriceResponse> getPrice(FilterPriceRequest filterPriceRequest) {

    return ResponseEntity.ok(getPriceUseCase.execute(filterPriceRequest));
  }
}
