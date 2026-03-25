package com.fipe.api2.application.exception;

public class VehicleNotFoundException extends BusinessException {
  public VehicleNotFoundException() {
    super(ErrorCode.VEHICLE_NOT_FOUND, "Vehicle Not Found");
  }
}
