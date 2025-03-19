package com.teste.testes.controller.Impl;

import com.teste.testes.DTO.car.RequestCarDTO;
import com.teste.testes.DTO.car.ResponseCarDTO;
import com.teste.testes.controller.CarController;
import com.teste.testes.model.Car;
import com.teste.testes.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarControllerImpl implements CarController {

    private final CarService carService;

    @GetMapping("/{carId}")
    @Override
    public ResponseEntity<Car> getCar(@PathVariable Long carId){
        return ResponseEntity.ok().body(carService.getCar(carId));
    }

    @PostMapping
    @Override
    public ResponseEntity<ResponseCarDTO> createCar(@RequestBody RequestCarDTO carDTO){
       return ResponseEntity.status(201).body(carService.createCar(carDTO));
    }

    @PutMapping("/{carId}")
    @Override
    public ResponseEntity<ResponseCarDTO> updateCar(@PathVariable Long carId, @RequestBody RequestCarDTO carDTO){
        return ResponseEntity.ok().body(carService.updateCar(carId, carDTO));
    }

    @DeleteMapping("/{carId}")
    @Override
    public ResponseEntity<String> deleteCar(@PathVariable Long carId){
        return ResponseEntity.ok().body(carService.deleteCar(carId));
    }
}
