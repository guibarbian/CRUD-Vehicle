package com.teste.testes.controller.Impl;

import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.DTO.ResponseVehicleDTO;
import com.teste.testes.controller.VehicleController;
import com.teste.testes.model.Vehicle;
import com.teste.testes.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleControllerImpl implements VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    @Override
    public ResponseEntity<List<ResponseVehicleDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ResponseVehicleDTO> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.findById(id));
    }

    @PostMapping
    @Override
    public ResponseEntity<ResponseVehicleDTO> createVehicle(@RequestBody RequestVehicleDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.createVehicle(request));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ResponseVehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody RequestVehicleDTO request) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, request));
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}
