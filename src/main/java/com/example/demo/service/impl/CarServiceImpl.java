package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.DTO.car.CreateCarRequest;
import com.example.demo.model.DTO.car.CreateCarResponse;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    @Override
    public Car getCar(Long carId){
        if(!carRepository.findById(carId).isPresent()){
            throw new  NotFoundException("Car not found");
        }

        return carRepository.findById(carId).get();
    }

    @Override
    public List<Car> getCars(){
        return carRepository.findAll();
    }

    @Override
    public CreateCarResponse createNewCar(CreateCarRequest request){

        carRepository.save(Car.builder()
                                    .brand(request.getBrand())
                                    .model(request.getModel())
                                    .manufacturingYear(request.getManufacturingYear())
                                    .numberOfDoors(request.getNumberOfDoors())
                                    .type("Car")
                                    .build());

        return CreateCarResponse.builder()
                                    .brand(request.getBrand())
                                    .model(request.getModel())
                                    .manufacturingYear(request.getManufacturingYear())
                                    .numberOfDoors(request.getNumberOfDoors())
                                    .type("Car")
                                    .build();
    }

    @Override
    public CreateCarResponse updateCar(Long carId, CreateCarRequest request){
        if(!carRepository.findById(carId).isPresent()){
            throw new  NotFoundException("Car not found");
        }

        carRepository.save(Car.builder()
                                    .id(carId)
                                    .brand(request.getBrand())
                                    .model(request.getModel())
                                    .manufacturingYear(request.getManufacturingYear())
                                    .numberOfDoors(request.getNumberOfDoors())
                                    .type("Car")
                                    .build());

        return CreateCarResponse.builder()
                                    .brand(request.getBrand())
                                    .model(request.getModel())
                                    .manufacturingYear(request.getManufacturingYear())
                                    .numberOfDoors(request.getNumberOfDoors())
                                    .type("Car")
                                    .build();
    }

    @Override
    public void deleteCar(Long carId){
        if(!carRepository.findById(carId).isPresent()){
            throw new  NotFoundException("Car not found");
        }

        carRepository.deleteById(carId);
    }
    
}
