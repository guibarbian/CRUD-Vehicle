package com.example.demo.controller;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.DTO.truck.CreateTruckRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Trucks", description = "Controller to managing truck endpoints")
public interface TruckController {
    
    @Operation(
        summary = "Get Truck",
        description = "Finds a Truck in the database",
        parameters = {
            @Parameter(name = "truckId", description = "Id of the truck to be found")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in finding truck"),
            @ApiResponse(responseCode = "404", description = "Did not found the truck in the database")
        }
    )
    public ResponseEntity<Object> getTruck(Long truckId);

    @Operation(
        summary = "Create Truck",
        description = "Creates a Truck in the database",
        parameters = {
            @Parameter(name = "request", description = "DTO with truck's info")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in creating truck"),
            @ApiResponse(responseCode = "404", description = "Did not found the truck in the database")
        }
    )
    public ResponseEntity<Object> createTruck(CreateTruckRequest request);

    @Operation(
        summary = "Update Truck",
        description = "Updates a Truck in the database",
        parameters = {
            @Parameter(name = "truckId", description = "Id of the truck to be updated"),
            @Parameter(name = "request", description = "DTO with truck's info")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in updating truck"),
            @ApiResponse(responseCode = "404", description = "Did not found the truck in the database")
        }
    )
    public ResponseEntity<Object> updateTruck(Long truckId, CreateTruckRequest request);

    @Operation(
        summary = "Delete Truck",
        description = "Deletes a Truck in the database",
        parameters = {
            @Parameter(name = "truckId", description = "Id of the truck to be deleted")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in deleting truck"),
            @ApiResponse(responseCode = "404", description = "Did not found the truck in the database")
        }
    )
    public ResponseEntity<Object> deleteTruck(Long truckId);
}
