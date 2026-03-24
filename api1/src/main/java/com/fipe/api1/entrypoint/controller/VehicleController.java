package com.fipe.api1.entrypoint.controller;

import com.fipe.api1.infrastructure.client.UpdateVehicleRequest;
import com.fipe.api1.infrastructure.client.VehicleClient;
import com.fipe.api1.infrastructure.client.VehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

  private final VehicleClient vehicleClient;

  @GetMapping("/{id}")
  public ResponseEntity<VehicleResponse> getBrands(@PathVariable Long id) {
    return ResponseEntity.ok(vehicleClient.getById(id));
  }

  @GetMapping("/brands")
  public ResponseEntity<List<String>> getBrands() {
    return ResponseEntity.ok(vehicleClient.getBrands());
  }

  @GetMapping
  public ResponseEntity<List<VehicleResponse>> getByBrand(@RequestParam String brand) {
    return ResponseEntity.ok(vehicleClient.getByBrand(brand));
  }

  @PutMapping("/{id}")
  public ResponseEntity<VehicleResponse> update(@PathVariable Long id, @RequestBody UpdateVehicleRequest request) {
    return ResponseEntity.ok(vehicleClient.updateVehicle(id, request));
  }

}
