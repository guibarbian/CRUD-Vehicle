package com.teste.testes.controller.Impl;

import com.teste.testes.DTO.truck.RequestTruckDTO;
import com.teste.testes.DTO.truck.ResponseTruckDTO;
import com.teste.testes.controller.TruckController;
import com.teste.testes.model.Truck;
import com.teste.testes.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trucks")
public class TruckControllerImpl implements TruckController {

    private final TruckService truckService;

    @GetMapping("/{truckId}")
    @Override
    public ResponseEntity<Truck> getTruck(@PathVariable Long truckId) {
        return ResponseEntity.ok(truckService.getTruck(truckId));
    }

    @PostMapping
    @Override
    public ResponseEntity<ResponseTruckDTO> createTruck(@RequestBody RequestTruckDTO requestTruckDTO) {
        return ResponseEntity.status(201).body(truckService.createTruck(requestTruckDTO));
    }

    @PutMapping("/{truckId}")
    @Override
    public ResponseEntity<ResponseTruckDTO> updateTruck(@PathVariable Long truckId, @RequestBody RequestTruckDTO requestTruckDTO) {
        return ResponseEntity.ok(truckService.updateTruck(truckId, requestTruckDTO));
    }

    @DeleteMapping("/{truckId}")
    @Override
    public ResponseEntity<String> deleteTruck(@PathVariable Long truckId){
        return ResponseEntity.ok(truckService.deleteTruck(truckId));
    }
}
