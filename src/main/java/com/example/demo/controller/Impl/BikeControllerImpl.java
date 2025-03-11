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

import com.example.demo.controller.BikeController;
import com.example.demo.model.DTO.bike.CreateBikeRequest;
import com.example.demo.service.impl.BikeServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bikes")
@RequiredArgsConstructor
public class BikeControllerImpl implements BikeController{

    private final BikeServiceImpl bikeService;

    @GetMapping("/{bikeId}")
    @Override
    public ResponseEntity<Object> getBike(@PathVariable Long bikeId){
        return ResponseEntity.ok().body(bikeService.getBike(bikeId));
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> createBike(@RequestBody CreateBikeRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(bikeService.createNewBike(request));
    }

    @PutMapping("/{bikeId}")
    @Override
    public ResponseEntity<Object> updateBike(@PathVariable Long bikeId, @RequestBody CreateBikeRequest request){
        return ResponseEntity.ok().body(bikeService.updateBike(bikeId, request));
    }

    @DeleteMapping("/{bikeId}")
    @Override
    public ResponseEntity<Object> deleteBike(@PathVariable Long bikeId){
        bikeService.deleteBike(bikeId);
        return ResponseEntity.ok().body("Bike deleted");
    }
    
}
