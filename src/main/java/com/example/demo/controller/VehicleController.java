package com.example.demo.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vehicles", description = "Controller to managing vehicles endpoints")
public interface VehicleController {
    
    @Operation(
        summary = "Get Vehicles",
        description = "Finds all Vehicles in database",
        responses = {
            @ApiResponse(responseCode = "200", description = "Succeeded in finding all vehicles")
        }
    )
    ResponseEntity<Object> getVehicles();
}
