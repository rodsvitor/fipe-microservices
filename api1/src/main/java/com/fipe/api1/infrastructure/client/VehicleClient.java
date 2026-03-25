package com.fipe.api1.infrastructure.client;

import com.fipe.api1.domain.Category;
import com.fipe.api1.infrastructure.client.dto.UpdateVehicleRequest;
import com.fipe.api1.infrastructure.client.dto.VehicleResponse;
import com.fipe.api1.infrastructure.client.support.RestClientExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class VehicleClient {

  private final RestTemplate restTemplate;
  private final RestClientExecutor restClientExecutor;
  private final String baseUrl;

  public VehicleClient(
      @Qualifier("api2RestTemplate") RestTemplate restTemplate,
      RestClientExecutor restClientExecutor,
      @Value("${api2.base-url}") String baseUrl) {

    this.restTemplate = restTemplate;
    this.restClientExecutor = restClientExecutor;
    this.baseUrl = baseUrl;

  }

  @Cacheable(value = "vehicle-brands", key = "'brands:' + (#category != null ? #category.name() : 'all')")
  public List<String> getAllBrands(Category category) {

    String url = baseUrl
        + "/internal/vehicles/brands"
        + (category != null ? ("?category=" + category) : "");

    return restClientExecutor.execute(() ->

        restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<String>>() {}
        ).getBody()

    );

  }

  @Cacheable(value = "vehicles-by-brand", key = "#brand.toLowerCase().trim() + ':' + (#category != null ? #category.name() : 'all')")
  public List<VehicleResponse> getByBrand(String brand, Category category) {

    String url = baseUrl + "/internal/vehicles?brand=" + brand
        + (category != null ? ("&category=" + category) : "");;

    return restClientExecutor.execute(() ->

        restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<VehicleResponse>>() {}
        ).getBody()

    );

  }

  @CacheEvict(value = "vehicles", allEntries = true)
  public VehicleResponse updateVehicle(Long id, UpdateVehicleRequest request) {

    String url = baseUrl + "/internal/vehicles/" + id;

    return restClientExecutor.execute(() ->

        restTemplate.exchange(
            url,
            HttpMethod.PUT,
            new HttpEntity<>(request),
            VehicleResponse.class
        ).getBody()

    );
  }

  @Cacheable(value = "vehicles-by-id", key = "#id")
  public VehicleResponse getById(Long id) {

    String url = baseUrl + "/internal/vehicles/" + id;

    return restClientExecutor.execute(() ->

        restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            VehicleResponse.class
        ).getBody()

    );

  }

}
