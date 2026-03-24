package com.fipe.api2.application.mapper;

import com.fipe.api2.application.dto.VehicleResponse;
import com.fipe.api2.domain.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapperDTO {

  VehicleResponse toResponse(Vehicle vehicle);

}
