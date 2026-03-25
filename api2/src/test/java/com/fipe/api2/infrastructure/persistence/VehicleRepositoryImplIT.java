package com.fipe.api2.infrastructure.persistence;

import com.fipe.api2.domain.model.Category;
import com.fipe.api2.domain.model.Vehicle;
import com.fipe.api2.domain.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class VehicleRepositoryImplIT {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Test
  void shouldSaveAndFindVehicle() {
    var vehicle = Vehicle.builder()
        .brand("Fiat")
        .model("Uno")
        .fipeBrandId(-1L)
        .fipeModelId(-1L)
        .category(Category.CAR)
        .build();

    vehicleRepository.save(vehicle);

    var result = vehicleRepository.findByBrand("Fiat");

    assertFalse(result.isEmpty());
  }
}