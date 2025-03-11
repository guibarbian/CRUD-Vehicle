package com.example.demo.controller;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.DTO.car.CreateCarRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cars", description = "Controller to managing car endpoints")
public interface CarController {
    
    @Operation(
        summary = "Get Car",
        description = "Finds a car in the database",
        parameters = {
            @Parameter(name = "carId", description = "Id of the car to be found")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in finding car"),
            @ApiResponse(responseCode = "404", description = "Did not found the car in the database")
        }
    )
    public ResponseEntity<Object> getCar(Long carId);

    @Operation(
        summary = "Create Car",
        description = "Creates a Car in the database",
        parameters = {
            @Parameter(name = "request", description = "DTO with car's info")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in creating car"),
            @ApiResponse(responseCode = "404", description = "Did not found the car in the database")
        }
    )
    public ResponseEntity<Object> createCar(CreateCarRequest request);

    @Operation(
        summary = "Update Car",
        description = "Updates a car in the database",
        parameters = {
            @Parameter(name = "carId", description = "Id of the car to be updated"),
            @Parameter(name = "request", description = "DTO with car's info")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in updating car"),
            @ApiResponse(responseCode = "404", description = "Did not found the car in the database")
        }
    )
    public ResponseEntity<Object> updateCar(Long carId, CreateCarRequest request);

    @Operation(
        summary = "Delete Car",
        description = "Deletes a Car in the database",
        parameters = {
            @Parameter(name = "carId", description = "Id of the car to be deleted")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in deleting car"),
            @ApiResponse(responseCode = "404", description = "Did not found the car in the database")
        }
    )
    public ResponseEntity<Object> deleteCar(Long carId);
}
