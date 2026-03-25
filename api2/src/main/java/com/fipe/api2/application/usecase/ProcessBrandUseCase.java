package com.fipe.api2.application.usecase;

import com.fipe.api2.domain.model.Category;
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

  public void execute(Long brandId, String brandName, Category category) {

    var response = fipeClient.getModelsByBrandId(category, brandId);

    if (response.models().isEmpty()) return;

    var vehicles = response.models().stream()
        .map(model -> Vehicle.builder()
            .fipeBrandId(brandId)
            .fipeModelId(model.id())
            .brand(brandName)
            .model(model.name())
            .category(category)
            .build())
        .toList();

    vehicleRepository.saveAll(vehicles);

  }

}
