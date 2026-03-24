package com.fipe.api2.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ModelDTO(

    @JsonProperty(value = "codigo")
    Long id,

    @JsonProperty(value = "nome")
    String name) {

}
