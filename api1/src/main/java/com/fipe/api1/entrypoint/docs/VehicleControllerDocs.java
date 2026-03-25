package com.fipe.api1.entrypoint.docs;

import com.fipe.api1.domain.Category;
import com.fipe.api1.infrastructure.client.dto.UpdateVehicleRequest;
import com.fipe.api1.infrastructure.client.dto.VehicleResponse;
import com.fipe.api1.infrastructure.exception.handler.BadRequestExceptionResponse;
import com.fipe.api1.infrastructure.exception.handler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Vehicles", description = "Operations related to vehicle management and queries")
public interface VehicleControllerDocs {

  @Operation(
      summary = "Get vehicle by ID",
      description = "Retrieve a vehicle by its unique identifier"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vehicle found"),
      @ApiResponse(responseCode = "404", description = "Vehicle not found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  ResponseEntity<VehicleResponse> getBrandsById(
      @Parameter(description = "Vehicle ID", example = "1")
      Long id
  );


  @Operation(
      summary = "Get all brands",
      description = "Retrieve all available vehicle brands, optionally filtered by category"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of brands returned"),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  ResponseEntity<List<String>> getAllBrands(
      @Parameter(
          description = "Vehicle category filter (e.g., CAR, MOTORCYCLE, TRUCK)",
          example = "CAR"
      )
      Category category
  );


  @Operation(
      summary = "Get vehicles by brand",
      description = "Retrieve vehicles filtered by brand name and optional category"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vehicles found"),
      @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  ResponseEntity<List<VehicleResponse>> getByBrand(
      @Parameter(description = "Vehicle brand name", example = "Toyota")
      String brand,

      @Parameter(
          description = "Vehicle category filter",
          example = "CAR"
      )
      Category category
  );


  @Operation(
      summary = "Update vehicle",
      description = "Update an existing vehicle's information"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vehicle updated successfully"),
      @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestExceptionResponse.class))),
      @ApiResponse(responseCode = "404", description = "Vehicle not found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  ResponseEntity<VehicleResponse> update(
      @Parameter(description = "Vehicle ID", example = "1")
      Long id,

      @Valid
      @RequestBody(
          description = "Vehicle data to update",
          required = true
      )
      UpdateVehicleRequest request
  );
}
