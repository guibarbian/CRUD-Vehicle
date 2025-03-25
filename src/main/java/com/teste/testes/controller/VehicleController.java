package com.teste.testes.controller;

import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.DTO.ResponseVehicleDTO;
import com.teste.testes.model.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleController {

    public ResponseEntity<List<Vehicle>> getAllVehicles();

    public ResponseEntity<Vehicle> getVehicleById(Long id);

    public ResponseEntity<ResponseVehicleDTO> createVehicle(RequestVehicleDTO request);

    public ResponseEntity<ResponseVehicleDTO> updateVehicle(Long id, RequestVehicleDTO request);

    public ResponseEntity<String> deleteVehicle(Long id);
}
