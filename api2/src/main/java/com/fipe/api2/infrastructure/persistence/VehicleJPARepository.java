package com.fipe.api2.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleJPARepository extends JpaRepository<VehicleEntity, Long> {

  @Query("SELECT DISTINCT v.brand FROM VehicleEntity v")
  List<String> findDistinctBrands();

  List<VehicleEntity> findByBrandContainingIgnoreCase(String name);

}