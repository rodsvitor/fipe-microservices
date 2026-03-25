package com.fipe.api2.infrastructure.client;

import com.fipe.api2.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FipeClient {

  private final RestTemplate restTemplate;

  @Value("${fipe-api.base-url}")
  private String fipeApiUrl;

  public FipeModelResponse getModelsByBrandId(Category category, Long brandId) {

    String url = fipeApiUrl + getCategoryResource(category, brandId);

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        FipeModelResponse.class
    ).getBody();
  }

  private static String getCategoryResource(Category category, Long brandId) {
    var resource = switch (category) {
      case CAR -> "/carros/marcas";
      case MOTORCYCLE -> "/motos/marcas";
      case TRUCK -> "/caminhoes/marcas";
    };

    return resource + "/" + brandId + "/modelos";
  }

}
