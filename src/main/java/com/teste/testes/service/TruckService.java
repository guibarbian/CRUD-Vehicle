package com.teste.testes.service;

import com.teste.testes.DTO.truck.RequestTruckDTO;
import com.teste.testes.DTO.truck.ResponseTruckDTO;
import com.teste.testes.model.Truck;

public interface TruckService {

    Truck getTruck(Long truckId);

    ResponseTruckDTO createTruck(RequestTruckDTO dto);

    ResponseTruckDTO updateTruck(Long truckId, RequestTruckDTO dto);

    String deleteTruck(Long truckId);
}
