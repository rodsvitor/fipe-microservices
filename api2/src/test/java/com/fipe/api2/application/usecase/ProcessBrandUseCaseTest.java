package com.fipe.api2.application.usecase;

import com.fipe.api2.application.dto.ModelDTO;
import com.fipe.api2.domain.model.Category;
import com.fipe.api2.domain.model.Vehicle;
import com.fipe.api2.domain.repository.VehicleRepository;
import com.fipe.api2.infrastructure.client.FipeClient;
import com.fipe.api2.infrastructure.client.FipeModelResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessBrandUseCaseTest {

  @Mock
  private FipeClient fipeClient;

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private ProcessBrandUseCase useCase;

  @Captor
  private ArgumentCaptor<List<Vehicle>> vehiclesCaptor;

  @Test
  void shouldFetchModelsAndSaveVehicles() {
    // Arrange
    var models = List.of(new ModelDTO(1L, "Fiat Uno"));

    when(fipeClient.getModelsByBrandId(Category.CAR, 1L))
        .thenReturn(new FipeModelResponse(models));

    // Act
    useCase.execute(1L, "Fiat", Category.CAR);

    // Assert
    verify(fipeClient).getModelsByBrandId(Category.CAR, 1L);
    verify(vehicleRepository).saveAll(vehiclesCaptor.capture());

    var savedVehicles = vehiclesCaptor.getValue();

    assertEquals(1, savedVehicles.size());

    var vehicle = savedVehicles.getFirst();
    assertEquals(1L, vehicle.getFipeBrandId());
    assertEquals(1L, vehicle.getFipeModelId());
    assertEquals("Fiat", vehicle.getBrand());
    assertEquals("Fiat Uno", vehicle.getModel());
    assertEquals(Category.CAR, vehicle.getCategory());
  }

  @Test
  void shouldSaveAllVehiclesWhenMultipleModelsReturned() {
    // Arrange
    var models = List.of(
        new ModelDTO(1L, "Uno"),
        new ModelDTO(2L, "Palio"),
        new ModelDTO(3L, "Toro")
    );

    when(fipeClient.getModelsByBrandId(Category.CAR, 1L))
        .thenReturn(new FipeModelResponse(models));

    // Act
    useCase.execute(1L, "Fiat", Category.CAR);

    // Assert
    verify(vehicleRepository).saveAll(vehiclesCaptor.capture());

    var savedVehicles = vehiclesCaptor.getValue();

    assertEquals(3, savedVehicles.size());

    assertEquals("Uno", savedVehicles.get(0).getModel());
    assertEquals("Palio", savedVehicles.get(1).getModel());
    assertEquals("Toro", savedVehicles.get(2).getModel());
  }

  @Test
  void shouldNotSaveWhenNoModelsReturned() {
    // Arrange
    when(fipeClient.getModelsByBrandId(Category.CAR, 1L))
        .thenReturn(new FipeModelResponse(List.of()));

    // Act
    useCase.execute(1L, "Fiat", Category.CAR);

    // Assert
    verify(vehicleRepository, never()).saveAll(any());
  }
}