package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Car;
import com.example.demo.model.DTO.car.CreateCarRequest;
import com.example.demo.model.DTO.car.CreateCarResponse;

public interface CarService{
    
    Car getCar(Long carId);

    List<Car> getCars();

    CreateCarResponse createNewCar(CreateCarRequest requestDto);

    CreateCarResponse updateCar(Long carId, CreateCarRequest requestDto);

    void deleteCar(Long carId);

}
