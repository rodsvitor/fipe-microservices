package com.fipe.api1.entrypoint.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponse(
    @Schema(
        description = "JWT token. Use as: Bearer <token>",
        example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String token) {}
