package com.fipe.api2.application.usecase;

import com.fipe.api2.application.dto.UpdateVehicleRequest;
import com.fipe.api2.application.dto.VehicleResponse;
import com.fipe.api2.application.mapper.VehicleMapperDTO;
import com.fipe.api2.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateVehicleUseCase {

  private final VehicleRepository repository;
  private final VehicleMapperDTO vehicleMapper;

  public VehicleResponse execute(Long id, UpdateVehicleRequest request) {

    var vehicle = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Vehicle not found"));

    vehicle.setModel(request.model());
    vehicle.setObservations(request.observations());

    return vehicleMapper.toResponse(repository.save(vehicle));

  }
}
