package com.teste.testes.controller;

import com.teste.testes.DTO.truck.RequestTruckDTO;
import com.teste.testes.DTO.truck.ResponseTruckDTO;
import com.teste.testes.model.Truck;
import org.springframework.http.ResponseEntity;

public interface TruckController {

    ResponseEntity<Truck> getTruck(Long truckId);

    ResponseEntity<ResponseTruckDTO> createTruck(RequestTruckDTO requestTruckDTO);

    ResponseEntity<ResponseTruckDTO> updateTruck(Long truckId, RequestTruckDTO requestTruckDTO);

    ResponseEntity<String> deleteTruck(Long truckId);
}
