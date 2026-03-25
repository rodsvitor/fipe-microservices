package com.fipe.api2.application.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
  private final ErrorCode code;

  protected BusinessException(ErrorCode errorCode, String message) {
    super(message);
    this.code = errorCode;
  }

}
