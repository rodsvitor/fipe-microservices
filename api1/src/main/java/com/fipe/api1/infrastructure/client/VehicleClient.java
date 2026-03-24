package com.fipe.api1.infrastructure.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VehicleClient {

  private final RestTemplate restTemplate;

  @Value("${api2.base-url}")
  private String baseUrl;

  @Cacheable(value = "vehicle-brands", key = "'all'")
  public List<String> getBrands() {

    var headers = new HttpHeaders();
    var entity = new HttpEntity<>(headers);

    String url = baseUrl + "/internal/vehicles/brands";

    List<String> brands = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        new ParameterizedTypeReference<List<String>>() {}
    ).getBody();

//    System.out.println("TYPE: " + brands.getClass());
//    System.out.println("FIRST ELEMENT TYPE: " + brands[0].getClass());

    return brands;

  }

  @Cacheable(value = "vehicles-by-brand", key = "#brand.toLowerCase().trim()")
  public List<VehicleResponse> getByBrand(String brand) {

    String url = baseUrl + "/internal/vehicles?brand=" + brand;

    var headers = new HttpHeaders();
    var entity = new HttpEntity<>(headers);

    List<VehicleResponse> brands = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        new ParameterizedTypeReference<List<VehicleResponse>>() {
        }
    ).getBody();

    System.out.println("TYPE: " + brands.getClass());

    return brands;

  }

  @CacheEvict(value = "vehicles", allEntries = true)
  public VehicleResponse updateVehicle(Long id, UpdateVehicleRequest request) {

    String url = baseUrl + "/internal/vehicles/" + id;

    var headers = new HttpHeaders();
    var entity = new HttpEntity<>(request, headers);

    ResponseEntity<VehicleResponse> response = restTemplate.exchange(
        url,
        HttpMethod.PUT,
        entity,
        VehicleResponse.class);

    return response.getBody();
  }


  @Cacheable(value = "vehicles-by-id", key = "#id")
  public VehicleResponse getById(Long id) {

    String url = baseUrl + "/internal/vehicles/" + id;

    var headers = new HttpHeaders();
    var entity = new HttpEntity<>(headers);

    ResponseEntity<VehicleResponse> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        VehicleResponse.class);

    return response.getBody();

  }
}
