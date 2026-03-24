package com.fipe.api2.entrypoint.controller;

import com.fipe.api2.application.dto.UpdateVehicleRequest;
import com.fipe.api2.application.dto.VehicleResponse;
import com.fipe.api2.application.service.VehicleQueryService;
import com.fipe.api2.application.usecase.UpdateVehicleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("internal/vehicles")
public class VehicleController {

  private final VehicleQueryService vehicleQueryService;
  private final UpdateVehicleUseCase updateVehicleUseCase;

  @GetMapping("/{id}")
  public ResponseEntity<VehicleResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(vehicleQueryService.findById(id));
  }

  @GetMapping("/brands")
  public ResponseEntity<List<String>> getBrands() {
    return ResponseEntity.ok(vehicleQueryService.findAllBrands());
  }

  @GetMapping
  public ResponseEntity<List<VehicleResponse>> getByBrand(@RequestParam String brand) {
    return ResponseEntity.ok(vehicleQueryService.findByBrand(brand));
  }

  @PutMapping("/{id}")
  public ResponseEntity<VehicleResponse> update(@PathVariable Long id, @RequestBody UpdateVehicleRequest request) {
    return ResponseEntity.ok(updateVehicleUseCase.execute(id, request));
  }

}
