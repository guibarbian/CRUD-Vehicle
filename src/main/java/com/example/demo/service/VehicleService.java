package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Vehicle;
import com.example.demo.model.DTO.vehicle.CreateVehicleRequest;
import com.example.demo.model.DTO.vehicle.CreateVehicleResponse;

public interface VehicleService {
    
    Vehicle getVehicle(Long vehicleId);

    List<Vehicle> getVehicles();

    CreateVehicleResponse createNewVehicle(CreateVehicleRequest dtoRequest);

    CreateVehicleResponse updateVehicle(Long vehicleId, CreateVehicleRequest dtoRequest);

    void deleteVehicle(Long vehicleId);
}
