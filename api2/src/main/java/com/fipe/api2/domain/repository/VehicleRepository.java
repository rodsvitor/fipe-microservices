package com.fipe.api2.domain.repository;

import com.fipe.api2.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

  Vehicle save(Vehicle vehicle);

  void saveAll(List<Vehicle> vehicles);

  Optional<Vehicle> findById(Long id);

  List<Vehicle> findByBrand(String brand);

  List<String> findDistinctBrands();

}