package com.fipe.api2.infrastructure.exception;

import com.fipe.api2.application.exception.BusinessException;
import com.fipe.api2.application.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionResponse> handleBusiness(BusinessException ex,
                                                          HttpServletRequest request) {

    log.error(ex.getMessage(), ex);
    HttpStatus status = mapToStatus(ex);

    ExceptionResponse error = ExceptionResponse.of(
        ex.getCode().name(),
        ex.getMessage(),
        status.value(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleGeneric(Exception ex, HttpServletRequest request) {

    log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ExceptionResponse.of(
            "INTERNAL_ERROR",
            "Unexpected error",
            500, request.getRequestURI()));
  }

  private HttpStatus mapToStatus(BusinessException ex) {

    if (ex.getCode() == ErrorCode.VEHICLE_NOT_FOUND) {
      return HttpStatus.NOT_FOUND;
    }

    return HttpStatus.BAD_REQUEST;
  }
}
