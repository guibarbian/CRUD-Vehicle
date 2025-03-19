package com.teste.testes.service;

import com.teste.testes.DTO.car.RequestCarDTO;
import com.teste.testes.DTO.car.ResponseCarDTO;
import com.teste.testes.model.Car;

public interface CarService {

    Car getCar(Long carId);

    ResponseCarDTO createCar(RequestCarDTO dto);

    ResponseCarDTO updateCar(Long carId, RequestCarDTO dto);

    String deleteCar(Long carId);
}
