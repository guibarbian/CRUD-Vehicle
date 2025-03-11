package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Truck;
import com.example.demo.model.DTO.truck.CreateTruckRequest;
import com.example.demo.model.DTO.truck.CreateTruckResponse;

public interface TruckService {
    
    Truck geTruck(Long truckId);

    List<Truck> getTrucks();

    CreateTruckResponse createNewTruck(CreateTruckRequest requestDto);

    CreateTruckResponse updateTruck(Long id, CreateTruckRequest requestDto);

    void deleteTruck(Long id);
}
