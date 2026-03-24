package com.fipe.api1.infrastructure.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FipeClient {

  private final RestTemplate restTemplate;

  public List<FipeBrandResponse> getBrands() {

    String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/";
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI4NzkzNmM2YS01NjU5LTQ1YzEtODFmZi02ODc4ZjdjNDhiMDMiLCJlbWFpbCI6InJvZHJpZ28ucnZzb3V6YUBnbWFpbC5jb20iLCJpYXQiOjE3NzQxNDU1MDB9.oxf8e6aEyVrUsAm1EL39GDiDz-KXJIZqBtipf6KQ798";

    var headers = new HttpHeaders();
    headers.setBearerAuth(token);

    var entity = new HttpEntity<>(headers);

    // TODO implement treatment for duplicates.

    ResponseEntity<FipeBrandResponse[]> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        FipeBrandResponse[].class);

    return Arrays.asList(response.getBody());

  }

}
