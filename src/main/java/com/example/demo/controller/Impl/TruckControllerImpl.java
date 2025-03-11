package com.example.demo.controller.Impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.TruckController;
import com.example.demo.model.DTO.truck.CreateTruckRequest;
import com.example.demo.service.impl.TruckServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trucks")
@RequiredArgsConstructor
public class TruckControllerImpl implements TruckController{

    private final TruckServiceImpl truckService;

    @GetMapping("/{truckId}")
    @Override
    public ResponseEntity<Object> getTruck(@PathVariable Long truckId){
        return ResponseEntity.ok().body(truckService.getTruck(truckId));
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> createTruck(@RequestBody CreateTruckRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(truckService.createNewTruck(request));
    }

    @PutMapping("/{truckId}")
    @Override
    public ResponseEntity<Object> updateTruck(@PathVariable Long truckId, @RequestBody CreateTruckRequest request){
        return ResponseEntity.ok().body(truckService.updateTruck(truckId, request));
    }

    @DeleteMapping("/{truckId}")
    @Override
    public ResponseEntity<Object> deleteTruck(@PathVariable Long truckId){
        truckService.deleteTruck(truckId);
        return ResponseEntity.ok().body("Truck deleted");
    }
    
}
