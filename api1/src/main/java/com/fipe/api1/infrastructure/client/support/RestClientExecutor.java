package com.fipe.api1.infrastructure.client.support;

import com.fipe.api1.infrastructure.exception.DownstreamServiceException;
import com.fipe.api1.infrastructure.exception.handler.ExceptionResponse;
import com.fipe.api1.infrastructure.serialization.JacksonJsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class RestClientExecutor {

  private final JacksonJsonMapper jsonMapper;

  public <T> T execute(Supplier<T> action) {
    try {

      return action.get();

    } catch (HttpStatusCodeException ex) {

      String responseBody = ex.getResponseBodyAsString();

      if (responseBody.isBlank()) {
        throw new RuntimeException(ex);
      }

      ExceptionResponse error = jsonMapper.deserialize(
          ex.getResponseBodyAsString(),
          ExceptionResponse.class
      );

      throw new DownstreamServiceException(error);
    }
  }

  public void executeVoid(Runnable action) {
    try {
      action.run();

    } catch (HttpStatusCodeException ex) {

      ExceptionResponse error = jsonMapper.deserialize(
          ex.getResponseBodyAsString(),
          ExceptionResponse.class
      );

      throw new DownstreamServiceException(error);
    }
  }
}
