package com.fipe.api1.infrastructure.exception.handler;

import com.fipe.api1.infrastructure.exception.DownstreamServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleValidation(
      MethodArgumentNotValidException ex,
      HttpServletRequest request) {

    log.warn("Validation error: {}", ex.getMessage());

    List<FieldErrorResponse> fieldErrors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> new FieldErrorResponse(
            error.getField(),
            error.getDefaultMessage(),
            error.getRejectedValue()
        ))
        .toList();

    BadRequestExceptionResponse response = BadRequestExceptionResponse.builder()
        .code("VALIDATION_ERROR")
        .message("Invalid request body")
        .status(HttpStatus.BAD_REQUEST.value())
        .path(request.getRequestURI())
        .errors(fieldErrors)
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(DownstreamServiceException.class)
  public ResponseEntity<ExceptionResponse> handleDownstream(
      DownstreamServiceException ex,
      HttpServletRequest request) {

    log.error(ex.getError().getMessage(), ex);

    ExceptionResponse error = ex.getError();
    error.setPath(request.getRequestURI());

    return ResponseEntity
        .status(error.getStatus())
        .body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleGeneric(Exception ex, HttpServletRequest request) {

    log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ExceptionResponse.of(
            "INTERNAL_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            request.getRequestURI()));
  }

}
