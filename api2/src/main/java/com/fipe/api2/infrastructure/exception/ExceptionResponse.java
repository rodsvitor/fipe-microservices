package com.fipe.api2.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
  private String code;
  private String message;
  private int status;
  private String path;
  private LocalDateTime timestamp;

  public static ExceptionResponse of(String code, String message, int status, String requestURI) {
    ExceptionResponse error = new ExceptionResponse();
    error.code = code;
    error.status = status;
    error.message = message;
    error.path = requestURI;
    error.timestamp = LocalDateTime.now();
    return error;
  }
}
