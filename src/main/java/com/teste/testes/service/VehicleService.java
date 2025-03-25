package com.teste.testes.service;

import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.DTO.ResponseVehicleDTO;
import com.teste.testes.model.Vehicle;

import java.util.List;

public interface VehicleService {

    public List<Vehicle> findAll();

    public Vehicle findById(Long vehicleId);

    public ResponseVehicleDTO createVehicle(RequestVehicleDTO dto);

    public ResponseVehicleDTO updateVehicle(Long vehicleId, RequestVehicleDTO dto);

    public void deleteVehicle(Long vehicleId);
}
