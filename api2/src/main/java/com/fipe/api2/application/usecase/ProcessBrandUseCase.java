package com.fipe.api2.application.usecase;

import com.fipe.api2.domain.model.Vehicle;
import com.fipe.api2.domain.repository.VehicleRepository;
import com.fipe.api2.infrastructure.client.FipeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessBrandUseCase {

  private final FipeClient fipeClient;
  private final VehicleRepository vehicleRepository;

  public void execute(Long brandId, String brandName) {

    var response = fipeClient.getModelsByBrand(brandId);

    var vehicles = response.models().stream()
        .map(model -> Vehicle.builder()
            .fipeBrandId(brandId)
            .fipeModelId(model.id())
            .brand(brandName)
            .model(model.name())
            .build())
        .toList();

    vehicleRepository.saveAll(vehicles);

  }

}
