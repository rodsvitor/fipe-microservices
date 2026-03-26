package com.fipe.api1.entrypoint.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @NotBlank
    @Schema(description = "Username for login", example = "admin")
    String username,

    @NotBlank
    @Schema(description = "Password for login", example = "admin")
    String password) {}
