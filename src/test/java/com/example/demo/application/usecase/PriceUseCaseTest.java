package com.example.demo.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.application.mapper.DomainToInfrastructureMapper;
import com.example.demo.application.mapper.DomainToInfrastructureMapperImpl;
import com.example.demo.application.port.inbound.GetPriceUseCase;
import com.example.demo.domain.exceptions.PriceNotFoundException;
import com.example.demo.domain.model.FilterPrice;
import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;
import com.example.demo.infrastructure.persistence.entity.MoneyEntity;
import com.example.demo.infrastructure.persistence.entity.PriceEntity;
import com.example.demo.infrastructure.persistence.repository.PriceRepositoryPort;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceUseCaseTest {

  @Mock private PriceRepositoryPort priceRepositoryPort;

  private DomainToInfrastructureMapper domainToInfrastructureMapper;
  private GetPriceUseCase getPriceUseCase;

  @BeforeEach
  void setup() {
    domainToInfrastructureMapper = new DomainToInfrastructureMapperImpl();
    getPriceUseCase = new PriceUseCase(domainToInfrastructureMapper, priceRepositoryPort);
  }

  @Test
  void execute_WithValidValues() {
    FilterPriceRequest filterPriceRequest = new FilterPriceRequest(35455L, 1, LocalDateTime.now());
    PriceEntity priceEntity = new PriceEntity();
    priceEntity.setId(1L);
    priceEntity.setProductId(35455L);
    priceEntity.setBrandId(1);
    priceEntity.setPriceList(4);
    priceEntity.setPriority(0);
    priceEntity.setStartDate(LocalDateTime.parse("2020-06-15T16:00:00"));
    priceEntity.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
    priceEntity.setAmount(new MoneyEntity(BigDecimal.valueOf(38.95), "EUR"));

    PriceResponse expected =
        new PriceResponse(
            35455L,
            1,
            4,
            LocalDateTime.parse("2020-06-15T16:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            BigDecimal.valueOf(38.95),
            "EUR");

    when(priceRepositoryPort.findTopPrice(any(FilterPrice.class)))
        .thenReturn(Optional.of(priceEntity));

    PriceResponse actual = getPriceUseCase.execute(filterPriceRequest);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void execute_WithNoValidValues() {
    FilterPriceRequest filterPriceRequest = new FilterPriceRequest(35455L, 10, LocalDateTime.now());

    PriceResponse expected =
        new PriceResponse(
            35455L,
            1,
            4,
            LocalDateTime.parse("2020-06-15T16:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            BigDecimal.valueOf(38.95),
            "EUR");

    when(priceRepositoryPort.findTopPrice(any(FilterPrice.class))).thenReturn(Optional.empty());

    assertThatThrownBy(() -> getPriceUseCase.execute(filterPriceRequest))
        .isInstanceOf(PriceNotFoundException.class);
  }
}
