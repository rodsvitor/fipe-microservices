package com.fipe.api1.entrypoint.controller;

import com.fipe.api1.domain.Category;
import com.fipe.api1.entrypoint.docs.VehicleControllerDocs;
import com.fipe.api1.infrastructure.client.VehicleClient;
import com.fipe.api1.infrastructure.client.dto.UpdateVehicleRequest;
import com.fipe.api1.infrastructure.client.dto.VehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VehicleController {
@RequestMapping("/v1/vehicles")
public class VehicleController implements VehicleControllerDocs {

  private final VehicleClient vehicleClient;

  @GetMapping("/{id}")
  public ResponseEntity<VehicleResponse> getBrandsById(@PathVariable Long id) {
    return ResponseEntity.ok(vehicleClient.getById(id));
  }

  @GetMapping("/brands")
  public ResponseEntity<List<String>> getAllBrands(@RequestParam(required = false) Category category) {
    return ResponseEntity.ok(vehicleClient.getAllBrands(category));
  }

  @GetMapping
  public ResponseEntity<List<VehicleResponse>> getByBrand(// TODO IMPLEMENTS PAGEABLE LATER
      @RequestParam String brand,
      @RequestParam(required = false) Category category) {
    return ResponseEntity.ok(vehicleClient.getByBrand(brand, category));
  }

  @PutMapping("/{id}")
  public ResponseEntity<VehicleResponse> update(@PathVariable Long id, @RequestBody UpdateVehicleRequest request) {
    return ResponseEntity.ok(vehicleClient.updateVehicle(id, request));
  }

}
