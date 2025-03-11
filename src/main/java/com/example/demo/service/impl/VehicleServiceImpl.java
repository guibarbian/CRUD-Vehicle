package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Vehicle;
import com.example.demo.model.DTO.vehicle.CreateVehicleRequest;
import com.example.demo.model.DTO.vehicle.CreateVehicleResponse;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;
    
    @Override
    public Vehicle getVehicle(Long vehicleId){
        if(!vehicleRepository.findById(vehicleId).isPresent()){
            throw new NotFoundException("Vehicle Not Found.");
        }

        return vehicleRepository.findById(vehicleId).get();
    }

    @Override
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    @Override
    public CreateVehicleResponse createNewVehicle(CreateVehicleRequest requestDto){
        vehicleRepository.save(Vehicle.builder()
                                        .type(requestDto.getType())
                                        .brand(requestDto.getBrand())
                                        .manufacturingYear(requestDto.getManufacturingYear())
                                        .model(requestDto.getModel())
                                        .build());

        return CreateVehicleResponse.builder()
                        .type(requestDto.getType())
                        .brand(requestDto.getBrand())
                        .manufacturingYear(requestDto.getManufacturingYear())
                        .model(requestDto.getModel())
                        .build();
    }


    @Override
    public CreateVehicleResponse updateVehicle(Long vehicleId, CreateVehicleRequest requestDto){
        if(!vehicleRepository.findById(vehicleId).isPresent()){
            throw new NotFoundException("Vehicle Not Found.");
        }

        vehicleRepository.save(Vehicle.builder()
                                        .id(vehicleId)
                                        .type(requestDto.getType())
                                        .brand(requestDto.getBrand())
                                        .manufacturingYear(requestDto.getManufacturingYear())
                                        .model(requestDto.getModel())
                                        .build());

        return CreateVehicleResponse.builder()
                        .type(requestDto.getType())
                        .brand(requestDto.getBrand())
                        .manufacturingYear(requestDto.getManufacturingYear())
                        .model(requestDto.getModel())
                        .build();
    }

    @Override
    public void deleteVehicle(Long vehicleId){
        if(!vehicleRepository.findById(vehicleId).isPresent()){
            throw new NotFoundException("Vehicle Not Found.");
        }

        vehicleRepository.deleteById(vehicleId);
    }

}
