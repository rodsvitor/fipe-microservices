package com.fipe.api2.application.service;

import com.fipe.api2.application.dto.VehicleResponse;
import com.fipe.api2.application.exception.VehicleNotFoundException;
import com.fipe.api2.application.mapper.VehicleMapperDTO;
import com.fipe.api2.domain.model.Category;
import com.fipe.api2.domain.model.Vehicle;
import com.fipe.api2.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleQueryService {

  private final VehicleRepository repository;
  private final VehicleMapperDTO vehicleMapperDTO;

  public List<VehicleResponse> findByBrand(String brand, Category category) {

    List<Vehicle> byBrand = category != null
        ? repository.findByBrandAndCategory(brand, category)
        : repository.findByBrand(brand);

    return byBrand
        .stream()
        .map(vehicleMapperDTO::toResponse)
        .toList();
  }

  public List<String> findAllBrands(Category category) {

    return category != null
        ? repository.findDistinctBrandsByCategory(category)
        : repository.findDistinctBrands();
  }

  public VehicleResponse findById(Long id) {
    return repository.findById(id)
        .map(vehicleMapperDTO::toResponse)
        .orElseThrow(VehicleNotFoundException::new);
  }

}
