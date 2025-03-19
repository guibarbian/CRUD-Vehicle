package com.teste.testes.controller;

import com.teste.testes.DTO.car.RequestCarDTO;
import com.teste.testes.DTO.car.ResponseCarDTO;
import com.teste.testes.model.Car;
import org.springframework.http.ResponseEntity;

public interface CarController {

    ResponseEntity<Car> getCar(Long carId);

    ResponseEntity<ResponseCarDTO> createCar(RequestCarDTO carDTO);

    ResponseEntity<ResponseCarDTO> updateCar(Long carId, RequestCarDTO carDTO);

    ResponseEntity<String> deleteCar(Long carId);
}
