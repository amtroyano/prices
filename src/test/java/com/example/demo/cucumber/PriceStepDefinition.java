package com.example.demo.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.domain.model.response.PriceResponse;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import java.math.BigDecimal;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PriceStepDefinition {

  private final RestTemplate restTemplate = new RestTemplate();

  @LocalServerPort private int port;

  private ResponseEntity<PriceResponse> response;

  private String brandId;
  private String productId;
  private String date;

  @Dado("que se solicita el precio para la cadena {int}, producto {long} y fecha {string}")
  public void prepareRequest(Integer brandId, Long productId, String date) {
    this.brandId = brandId.toString();
    this.productId = productId.toString();
    this.date = date;
  }

  @Cuando("se realiza la consulta al servicio de precios")
  public void executeRequest() {
    String url =
        String.format(
            "http://localhost:%d/api/prices?productId=%s&brandId=%s&dateToSearch=%s",
            port, productId, brandId, date);

    response = restTemplate.getForEntity(url, PriceResponse.class);
  }

  @Entonces("el código de respuesta es {int}")
  public void verifyStatus(int statusCode) {
    assertEquals(statusCode, response.getStatusCode().value());
  }

  @Y("el precio final es {bigDecimal}")
  public void verifyPrice(BigDecimal expectedPrice) {
    assertNotNull(response.getBody());
    assertEquals(expectedPrice, response.getBody().finalPrice());
  }

  @Y("la tarifa aplicada es la {int}")
  public void verifyPriceList(int expectedList) {
    assertNotNull(response.getBody());
    assertEquals(expectedList, response.getBody().priceList());
  }
}
