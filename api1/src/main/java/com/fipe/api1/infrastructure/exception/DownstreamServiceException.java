package com.fipe.api1.infrastructure.exception;

import com.fipe.api1.infrastructure.exception.handler.ExceptionResponse;
import lombok.Getter;

@Getter
public class DownstreamServiceException extends RuntimeException {

  private final ExceptionResponse error;

  public DownstreamServiceException(ExceptionResponse error) {
    super(error.getMessage());
    this.error = error;
  }

}
