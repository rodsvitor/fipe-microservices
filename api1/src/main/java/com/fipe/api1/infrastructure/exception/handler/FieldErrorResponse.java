package com.fipe.api1.infrastructure.exception.handler;

public record FieldErrorResponse(
    String field,
    String message,
    Object rejectedValue
) {}
