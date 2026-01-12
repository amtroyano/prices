package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @ParameterizedTest
  @CsvSource({
    "2020-06-14T10:00:00, 35.50, 1",
    "2020-06-14T16:00:00, 25.45, 2",
    "2020-06-14T21:00:00, 35.50, 1",
    "2020-06-15T10:00:00, 30.50, 3",
    "2020-06-16T21:00:00, 38.95, 4"
  })
  void validateInditexCasuistics(String date, Double expectedPrice, Integer expectedList)
      throws Exception {
    mockMvc
        .perform(
            get("/api/prices")
                .param("brandId", "1")
                .param("productId", "35455")
                .param("applicationDate", date)
                .contentType(MediaType.APPLICATION_JSON.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.finalPrice").value(expectedPrice))
        .andExpect(jsonPath("$.priceList").value(expectedList))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.productId").value(35455));
  }

  @Test
  void shouldReturn404_WhenNoPriceFound() throws Exception {
    mockMvc
        .perform(
            get("/api/prices")
                .param("brandId", "1")
                .param("productId", "99999")
                .param("applicationDate", "2026-01-01T10:00:00"))
        .andExpect(status().isNotFound());
  }
}
