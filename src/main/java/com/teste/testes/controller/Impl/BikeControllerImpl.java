package com.teste.testes.controller.Impl;

import com.teste.testes.DTO.bike.RequestBikeDTO;
import com.teste.testes.DTO.bike.ResponseBikeDTO;
import com.teste.testes.controller.BikeController;
import com.teste.testes.model.Bike;
import com.teste.testes.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bikes")
public class BikeControllerImpl implements BikeController {

    private final BikeService bikeService;

    @GetMapping("/{bikeId}")
    @Override
    public ResponseEntity<Bike> getBike(@PathVariable Long bikeId) {
        return ResponseEntity.ok(bikeService.getBike(bikeId));
    }

    @PostMapping
    @Override
    public ResponseEntity<ResponseBikeDTO> createBike(@RequestBody RequestBikeDTO requestBikeDTO) {
        return ResponseEntity.status(201).body(bikeService.createBike(requestBikeDTO));
    }

    @PutMapping("/{bikeId}")
    @Override
    public ResponseEntity<ResponseBikeDTO> updateBike(@PathVariable Long bikeId, @RequestBody RequestBikeDTO requestBikeDTO) {
        return ResponseEntity.ok(bikeService.updateBike(bikeId, requestBikeDTO));
    }

    @DeleteMapping("/{bikeId}")
    @Override
    public ResponseEntity<String> deleteBike(@PathVariable Long bikeId) {
        return ResponseEntity.ok(bikeService.deleteBike(bikeId));
    }
}
