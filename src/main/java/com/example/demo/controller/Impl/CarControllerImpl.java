package com.example.demo.controller.Impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.CarController;
import com.example.demo.model.DTO.car.CreateCarRequest;
import com.example.demo.service.impl.CarServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarControllerImpl implements CarController{

    private final CarServiceImpl carService;

    @GetMapping("/{carId}")
    @Override
    public ResponseEntity<Object> getCar(@PathVariable Long carId){
        return ResponseEntity.ok().body(carService.getCar(carId));
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> createCar(@RequestBody CreateCarRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.createNewCar(request));
    }

    @PutMapping("/{carId}")
    @Override
    public ResponseEntity<Object> updateCar(@PathVariable Long carId, @RequestBody CreateCarRequest request){
        return ResponseEntity.ok().body(carService.updateCar(carId, request));
    }

    @DeleteMapping("/{carId}")
    @Override
    public ResponseEntity<Object> deleteCar(@PathVariable Long carId){
        carService.deleteCar(carId);
        return ResponseEntity.ok().body("Car deleted");
    }
    
}
