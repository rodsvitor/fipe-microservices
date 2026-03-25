package com.fipe.api2.infrastructure.persistence;

import com.fipe.api2.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleJPARepository extends JpaRepository<VehicleEntity, Long> {

  @Query("""
        SELECT DISTINCT v.brand
        FROM VehicleEntity v
        ORDER BY v.brand""")
  List<String> findDistinctBrands();

  @Query("""
        SELECT DISTINCT v.brand
        FROM VehicleEntity v
        WHERE v.category = :category
        ORDER BY v.brand""")
  List<String> findDistinctBrandsByCategory(@Param("category") Category category);

  List<VehicleEntity> findByBrandIgnoreCase(String name);

  List<VehicleEntity> findByBrandIgnoreCaseAndCategory(String name, Category category);

}