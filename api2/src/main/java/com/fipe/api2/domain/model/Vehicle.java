package com.fipe.api2.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {

  private Long id;

  private Long fipeBrandId;

  private Long fipeModelId;

  private String brand;

  private String code;

  private String model;

  private String observations;

}
