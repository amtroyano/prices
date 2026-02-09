package com.example.demo.application.port.inbound;

// import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
// import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;

import com.example.demo.infrastructure.adapter.dto.FilterPriceRequest;
import com.example.demo.infrastructure.adapter.dto.PriceResponse;

public interface GetPriceUseCase {
  PriceResponse execute(FilterPriceRequest filterPriceRequest);
}
