package com.fipe.api1.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FipeBrandResponse(
    @JsonProperty(value = "codigo")
    Long id,

    @JsonProperty(value = "nome")
    String name) {
}
