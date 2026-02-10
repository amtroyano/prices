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
import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import com.example.demo.infrastructure.persistence.entity.MoneyEntity;
import com.example.demo.infrastructure.persistence.entity.PriceEntity;
import com.example.demo.infrastructure.persistence.repository.PriceRepositoryPort;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
  private static final String DATE_TO_SEARCH_VALUE = "2020-11-30T11:00:00+01:00";
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
    PriceEntity priceEntity = new PriceEntity();
    priceEntity.setId(1L);
    priceEntity.setProductId(PRODUCT_ID_VALUE);
    priceEntity.setBrandId(BRAND_ID_VALUE);
    priceEntity.setPriceList(4);
    priceEntity.setPriority(0);
    priceEntity.setStartDate(OffsetDateTime.parse("2020-06-15T16:00:00+01:00"));
    priceEntity.setEndDate(OffsetDateTime.parse("2020-12-31T23:59:59+01:00"));
    priceEntity.setAmount(new MoneyEntity(BigDecimal.valueOf(FINAL_PRICE), CURRENCY));

    PriceResponse expected =
        PriceResponse.builder()
            .productId(PRODUCT_ID_VALUE)
            .brandId(BRAND_ID_VALUE)
            .priceList(4)
            .startDate(OffsetDateTime.parse("2020-06-15T16:00:00+01:00"))
            .endDate(OffsetDateTime.parse("2020-12-31T23:59:59+01:00"))
            .finalPrice(BigDecimal.valueOf(FINAL_PRICE))
            .currency(CURRENCY)
            .build();

    when(priceRepositoryPort.findTopPrice(any(FilterPrice.class)))
        .thenReturn(Optional.of(priceEntity));

    PriceResponse actual =
        getPriceUseCase.execute(
            PRODUCT_ID_VALUE, BRAND_ID_VALUE, OffsetDateTime.parse(DATE_TO_SEARCH_VALUE));

    ArgumentCaptor<FilterPrice> priceEntityCaptor = ArgumentCaptor.forClass(FilterPrice.class);
    verify(priceRepositoryPort).findTopPrice(priceEntityCaptor.capture());
    FilterPrice filterPrice = priceEntityCaptor.getValue();
    assertThat(filterPrice.brandId()).isEqualTo(BRAND_ID_VALUE);
    assertThat(filterPrice.productId()).isEqualTo(PRODUCT_ID_VALUE);
    assertThat(filterPrice.dateToSearch()).isEqualTo(DATE_TO_SEARCH_VALUE);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void execute_WithNoValidValues() {
    when(priceRepositoryPort.findTopPrice(any(FilterPrice.class))).thenReturn(Optional.empty());

    assertThatThrownBy(
            () ->
                getPriceUseCase.execute(
                    PRODUCT_ID_VALUE, 10, OffsetDateTime.parse(DATE_TO_SEARCH_VALUE)))
        .isInstanceOf(PriceNotFoundException.class);
  }
}
