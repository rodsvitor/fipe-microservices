package com.fipe.api1.infrastructure.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipe.api1.infrastructure.exception.SerializationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JacksonJsonMapper {

  private final ObjectMapper objectMapper;

  public <T> T deserialize(String body, Class<T> _class) {

    try {
      return objectMapper.readValue(body, _class);
    } catch (JsonProcessingException e) {
      throw new SerializationException(e);
    }

  }

}
