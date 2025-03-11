package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.service.VehicleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final CarServiceImpl carService;
    private final BikeServiceImpl bikeService;
    private final TruckServiceImpl truckService;

    @Override
    public List<Object> getVehicles(){
        List<Object> vehicles = List.of();
        
        vehicles.add(carService.getCars());
        vehicles.add(bikeService.getBikes());
        vehicles.add(truckService.getTrucks());
        return vehicles;
    }
    
}
