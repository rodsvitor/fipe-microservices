package com.fipe.api1.infrastructure.client;

import com.fipe.api1.domain.Category;
import com.fipe.api1.infrastructure.client.dto.FipeBrandResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FipeClient {

  private final RestTemplate restTemplate;
  private final String baseUrl;

  public FipeClient(
      @Qualifier("fipeRestTemplate") RestTemplate restTemplate,
      @Value("${fipe-api.base-url}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  public List<FipeBrandResponse> getBrands(Category category) {

    String url = baseUrl + getCategoryResource(category);

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<FipeBrandResponse>>() {}
    ).getBody();
  }

  private static String getCategoryResource(Category category) {
    return switch (category) {
      case CAR -> "/carros/marcas/";
      case MOTORCYCLE -> "/motos/marcas/";
      case TRUCK -> "/caminhoes/marcas/";
    };
  }

}
