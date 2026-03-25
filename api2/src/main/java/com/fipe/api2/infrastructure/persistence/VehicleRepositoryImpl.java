package com.fipe.api2.infrastructure.persistence;

import com.fipe.api2.domain.model.Category;
import com.fipe.api2.domain.model.Vehicle;
import com.fipe.api2.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleRepositoryImpl implements VehicleRepository {

  private final VehicleJPARepository jpaRepository;
  private final VehicleMapperORM vehicleMapperORM;

  @Override
  public Vehicle save(Vehicle vehicle) {
    VehicleEntity entity = vehicleMapperORM.toEntity(vehicle);

    entity = jpaRepository.save(entity);

    return vehicleMapperORM.toDomain(entity);
  }

  @Override
  public void saveAll(List<Vehicle> vehicles) {

    List<VehicleEntity> vehiclesEntity = vehicles
        .stream()
        .map(vehicleMapperORM::toEntity)
        .toList();

    jpaRepository.saveAll(vehiclesEntity);

  }

  @Override
  public Optional<Vehicle> findById(Long id) {
    return jpaRepository.findById(id)
        .map(vehicleMapperORM::toDomain);
  }

  @Override
  public List<Vehicle> findByBrand(String brand) {
    return jpaRepository.findByBrandIgnoreCase(brand)
        .stream()
        .map(vehicleMapperORM::toDomain)
        .toList();
  }

  @Override
  public List<Vehicle> findByBrandAndCategory(String brand, Category category) {
    return jpaRepository.findByBrandIgnoreCaseAndCategory(brand, category)
        .stream()
        .map(vehicleMapperORM::toDomain)
        .toList();
  }

  @Override
  public List<String> findDistinctBrands() {
    return jpaRepository.findDistinctBrands();
  }

  @Override
  public List<String> findDistinctBrandsByCategory(Category category) {
    return jpaRepository.findDistinctBrandsByCategory(category);
  }
}
