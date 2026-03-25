package com.fipe.api1.infrastructure.config.http;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FipeRequestInterceptor implements ClientHttpRequestInterceptor {

  @Value("${fipe-api.key}")
  private String fipeApiKey;

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request,
      byte[] body,
      ClientHttpRequestExecution execution
  ) throws IOException {

    if (Strings.isNotBlank(fipeApiKey)) {
      request.getHeaders().setBearerAuth(fipeApiKey); // Adds key to perform up to 1000 operations per day.
    }

    return execution.execute(request, body);
  }
}
