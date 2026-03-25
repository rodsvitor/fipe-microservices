package com.fipe.api2.application.dto;

import com.fipe.api2.domain.model.Category;

public record VehicleResponse(
    Long id,
    String fipeModelId,
    String brand,
    String model,
    Category category,
    String observations) {}
