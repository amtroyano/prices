package com.example.demo.application.port.inbound;

import com.example.demo.domain.model.request.FilterPriceRequest;
import com.example.demo.domain.model.response.PriceResponse;

public interface GetPriceUseCase {
  PriceResponse execute(FilterPriceRequest filterPriceRequest);
}
