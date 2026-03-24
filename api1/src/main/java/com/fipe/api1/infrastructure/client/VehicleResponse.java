package com.fipe.api1.infrastructure.client;

import java.io.Serializable;

public record VehicleResponse(
    Long id,
    String fipeModelId,
    String brand,
    String model,
    String observations) implements Serializable {}
