package com.fipe.api1.infrastructure.config.http;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

  private final InternalApi2RequestInterceptor internalApiInterceptor;
  private final FipeRequestInterceptor fipeRequestInterceptor;

  @Bean
  @Qualifier("api2RestTemplate")
  public RestTemplate api2RestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add(internalApiInterceptor);

    return restTemplate;
  }

  @Bean
  @Qualifier("fipeRestTemplate")
  public RestTemplate fipeRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add(fipeRequestInterceptor);

    return restTemplate;
  }

}
