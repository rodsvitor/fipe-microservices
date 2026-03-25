package com.fipe.api1.entrypoint.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @NotBlank
    String username,

    @NotBlank
    String password) {}
