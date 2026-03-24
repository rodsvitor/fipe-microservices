package com.fipe.api2.infrastructure.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fipe.api2.application.dto.ModelDTO;

import java.util.List;

public record FipeModelResponse(
    @JsonProperty("modelos") List<ModelDTO> models) {
}
