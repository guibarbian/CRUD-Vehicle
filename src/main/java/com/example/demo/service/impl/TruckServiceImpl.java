package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Truck;
import com.example.demo.model.DTO.truck.CreateTruckRequest;
import com.example.demo.model.DTO.truck.CreateTruckResponse;
import com.example.demo.repository.TruckRepository;
import com.example.demo.service.TruckService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TruckServiceImpl implements TruckService{

    private final TruckRepository truckRepository;

    @Override
    public Truck getTruck(Long truckId){
        if(!truckRepository.findById(truckId).isPresent()){
            throw new  NotFoundException("Truck not found");
        }

        return truckRepository.findById(truckId).get();
    }

    @Override
    public List<Truck> getTrucks(){
        return truckRepository.findAll();
    }

    @Override
    public CreateTruckResponse createNewTruck(CreateTruckRequest request){

        truckRepository.save(Truck.builder()
                                    .brand(request.getBrand())
                                    .model(request.getModel())
                                    .manufacturingYear(request.getManufacturingYear())
                                    .cargoCapacity(request.getCargoCapacity())
                                    .type("Truck")
                                    .build());

        return CreateTruckResponse.builder()
                                    .brand(request.getBrand())
                                    .model(request.getModel())
                                    .manufacturingYear(request.getManufacturingYear())
                                    .cargoCapacity(request.getCargoCapacity())
                                    .type("Truck")
                                    .build();
    }

    @Override
    public CreateTruckResponse updateTruck(Long truckId, CreateTruckRequest request){
        if(!truckRepository.findById(truckId).isPresent()){
            throw new  NotFoundException("Truck not found");
        }

        truckRepository.save(Truck.builder()
                                    .id(truckId)
                                    .brand(request.getBrand())
                                    .model(request.getModel())
                                    .manufacturingYear(request.getManufacturingYear())
                                    .cargoCapacity(request.getCargoCapacity())
                                    .type("Truck")
                                    .build());

        return CreateTruckResponse.builder()
                                    .brand(request.getBrand())
                                    .model(request.getModel())
                                    .manufacturingYear(request.getManufacturingYear())
                                    .cargoCapacity(request.getCargoCapacity())
                                    .type("Truck")
                                    .build();
    }

    @Override
    public void deleteTruck(Long truckId){
        if(!truckRepository.findById(truckId).isPresent()){
            throw new  NotFoundException("Truck not found");
        }

        truckRepository.deleteById(truckId);
    }
    
}
