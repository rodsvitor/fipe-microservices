package com.fipe.api1.infrastructure.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class BadRequestExceptionResponse extends ExceptionResponse{

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<FieldErrorResponse> errors;

}
