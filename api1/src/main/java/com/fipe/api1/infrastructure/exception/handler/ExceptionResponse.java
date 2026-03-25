package com.fipe.api1.infrastructure.exception.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class ExceptionResponse {
  private String code;
  private String message;
  private int status;
  private String path;
  private LocalDateTime timestamp;

  public static ExceptionResponse of(String code, String message, int status, String requestURI) {

    return ExceptionResponse.builder()
        .code(code)
        .status(status)
        .message(message)
        .path(requestURI)
        .timestamp(LocalDateTime.now())
        .build();

  }
}
