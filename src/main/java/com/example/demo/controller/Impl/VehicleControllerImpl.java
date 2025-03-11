package com.example.demo.controller.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.VehicleController;
import com.example.demo.service.impl.VehicleServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleControllerImpl implements VehicleController{
    
    private final VehicleServiceImpl vehicleService;

    @GetMapping
    @Override
    public ResponseEntity<Object> getVehicles(){
        return ResponseEntity.ok().body(vehicleService.getVehicles());
    }
}
