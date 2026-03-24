package com.fipe.api2.application.dto;

public record VehicleResponse(
    Long id,
    String fipeModelId,
    String brand,
    String model,
    String observations) {}
