package com.fipe.api1.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fipe.api1.domain.Category;

import java.io.Serializable;

public record VehicleResponse(
    Long id,
    String fipeModelId,
    String brand,
    String model,
    Category category,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String observations) implements Serializable {}
