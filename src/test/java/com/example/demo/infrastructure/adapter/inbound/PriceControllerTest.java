package com.example.demo.infrastructure.adapter.inbound;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.application.port.inbound.GetPriceUseCase;
import com.example.demo.domain.exceptions.PriceNotFoundException;
import com.example.demo.infrastructure.adapter.inbound.request.FilterPriceRequest;
import com.example.demo.infrastructure.adapter.inbound.response.PriceResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PriceController.class)
public class PriceControllerTest {

  private static final String PATH = "/api/prices";

  private static final String PRODUCT_ID_PARAM = "productId";
  private static final String PRODUCT_ID_VALUE = "35455";
  private static final String BRAND_ID_PARAM = "brandId";
  private static final String BRAND_ID_VALUE = "1";
  private static final String DATE_TO_SEARCH_PARAM = "dateToSearch";
  private static final String DATE_TO_SEARCH_VALUE = "2020-06-14T10:00:00";

  private static final Double FINAL_PRICE = 38.95;
  private static final String CURRENCY = "EUR";

  @Autowired private MockMvc mockMvc;

  @MockitoBean private GetPriceUseCase getPriceUseCase;

  @BeforeEach
  void setup() {}

  @Test
  void getPrice() throws Exception {
    PriceResponse expected =
        new PriceResponse(
            35455L,
            1,
            4,
            LocalDateTime.parse("2020-06-15T16:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            BigDecimal.valueOf(FINAL_PRICE),
            CURRENCY);

    when(getPriceUseCase.execute(any(FilterPriceRequest.class))).thenReturn(expected);

    mockMvc
        .perform(
            get(PATH)
                .param(PRODUCT_ID_PARAM, PRODUCT_ID_VALUE)
                .param(BRAND_ID_PARAM, BRAND_ID_VALUE)
                .param(DATE_TO_SEARCH_PARAM, DATE_TO_SEARCH_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.finalPrice").value(FINAL_PRICE))
        .andExpect(jsonPath("$.currency").value(CURRENCY));
  }

  @Test
  void getPrice_BadRequest() throws Exception {
    mockMvc
        .perform(
            get(PATH)
                .param(PRODUCT_ID_PARAM, PRODUCT_ID_VALUE)
                .param(DATE_TO_SEARCH_PARAM, DATE_TO_SEARCH_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getPrice_NotFound() throws Exception {
    when(getPriceUseCase.execute(any(FilterPriceRequest.class)))
        .thenThrow(PriceNotFoundException.class);

    mockMvc
        .perform(
            get(PATH)
                .param(PRODUCT_ID_PARAM, PRODUCT_ID_VALUE)
                .param(BRAND_ID_PARAM, BRAND_ID_VALUE)
                .param(DATE_TO_SEARCH_PARAM, DATE_TO_SEARCH_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
