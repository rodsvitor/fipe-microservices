package com.fipe.api1.infrastructure.client.dto;


import jakarta.validation.constraints.NotBlank;

public record UpdateVehicleRequest(
    @NotBlank
    String model,

    String observations) {}
