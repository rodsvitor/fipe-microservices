package com.fipe.api1.infrastructure.config.http;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InternalApi2RequestInterceptor implements ClientHttpRequestInterceptor {

  @Value("${api2.internal.header}")
  private String headerName;

  @Value("${api2.internal.key}")
  private String apiKey;

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request,
      byte @NonNull [] body,
      ClientHttpRequestExecution execution
  ) throws IOException {

    request.getHeaders().set(headerName, apiKey);
    return execution.execute(request, body);

  }
}
