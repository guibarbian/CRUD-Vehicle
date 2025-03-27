package com.teste.testes.service;

import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.DTO.ResponseVehicleDTO;
import com.teste.testes.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<ResponseVehicleDTO> findAll();

    ResponseVehicleDTO findById(Long vehicleId);

    ResponseVehicleDTO createVehicle(RequestVehicleDTO dto);

    ResponseVehicleDTO updateVehicle(Long vehicleId, RequestVehicleDTO dto);

    void deleteVehicle(Long vehicleId);
}
