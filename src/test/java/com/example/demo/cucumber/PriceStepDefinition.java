package com.example.demo.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.infrastructure.adapter.dto.PriceResponse;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hc.core5.net.URIBuilder;
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
  public void executeRequest() throws URISyntaxException {
    URI uri =
        new URIBuilder()
            .setScheme("http")
            .setHost("localhost")
            .setPort(port)
            .setPath("/v1/api/prices")
            .addParameter("productId", productId)
            .addParameter("brandId", brandId)
            .addParameter("dateToSearch", date)
            .build();

    response = restTemplate.getForEntity(uri, PriceResponse.class);
  }

  @Entonces("el código de respuesta es {int}")
  public void verifyStatus(int statusCode) {
    assertEquals(statusCode, response.getStatusCode().value());
  }

  @Y("el precio final es {string}")
  public void verifyPrice(String expectedPrice) {
    assertNotNull(response.getBody());
    assertEquals(
        new BigDecimal(expectedPrice.replace(",", ".")), response.getBody().getFinalPrice());
  }

  @Y("la tarifa aplicada es la {int}")
  public void verifyPriceList(int expectedList) {
    assertNotNull(response.getBody());
    assertEquals(expectedList, response.getBody().getPriceList());
  }
}
