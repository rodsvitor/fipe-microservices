package com.fipe.api2.infrastructure.persistence;

import com.fipe.api2.domain.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapperORM {

  VehicleEntity toEntity(Vehicle vehicle);

  Vehicle toDomain(VehicleEntity vehicle);

}
