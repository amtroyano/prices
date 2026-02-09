package com.example.demo.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceUseCaseTest {

  private static final Long PRODUCT_ID_VALUE = 35455L;
  private static final Integer BRAND_ID_VALUE = 1;
  private static final Double FINAL_PRICE = 38.95;
  private static final String CURRENCY = "EUR";

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
    FilterPriceRequest filterPriceRequest =
        new FilterPriceRequest(PRODUCT_ID_VALUE, BRAND_ID_VALUE, LocalDateTime.now());
    PriceEntity priceEntity = new PriceEntity();
    priceEntity.setId(1L);
    priceEntity.setProductId(PRODUCT_ID_VALUE);
    priceEntity.setBrandId(BRAND_ID_VALUE);
    priceEntity.setPriceList(4);
    priceEntity.setPriority(0);
    priceEntity.setStartDate(LocalDateTime.parse("2020-06-15T16:00:00"));
    priceEntity.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
    priceEntity.setAmount(new MoneyEntity(BigDecimal.valueOf(FINAL_PRICE), CURRENCY));

    PriceResponse expected =
        new PriceResponse(
            PRODUCT_ID_VALUE,
            BRAND_ID_VALUE,
            4,
            LocalDateTime.parse("2020-06-15T16:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            BigDecimal.valueOf(FINAL_PRICE),
            CURRENCY);

    when(priceRepositoryPort.findTopPrice(any(FilterPrice.class)))
        .thenReturn(Optional.of(priceEntity));

    PriceResponse actual = getPriceUseCase.execute(filterPriceRequest);

    ArgumentCaptor<FilterPrice> priceEntityCaptor = ArgumentCaptor.forClass(FilterPrice.class);
    verify(priceRepositoryPort).findTopPrice(priceEntityCaptor.capture());
    FilterPrice filterPrice = priceEntityCaptor.getValue();
    assertThat(filterPrice.brandId()).isEqualTo(filterPriceRequest.brandId());
    assertThat(filterPrice.productId()).isEqualTo(filterPriceRequest.productId());
    assertThat(filterPrice.dateToSearch()).isEqualTo(filterPriceRequest.dateToSearch());

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void execute_WithNoValidValues() {
    FilterPriceRequest filterPriceRequest =
        new FilterPriceRequest(PRODUCT_ID_VALUE, 10, LocalDateTime.now());

    when(priceRepositoryPort.findTopPrice(any(FilterPrice.class))).thenReturn(Optional.empty());

    assertThatThrownBy(() -> getPriceUseCase.execute(filterPriceRequest))
        .isInstanceOf(PriceNotFoundException.class);
  }
}
