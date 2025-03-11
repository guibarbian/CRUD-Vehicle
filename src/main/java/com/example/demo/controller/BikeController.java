package com.example.demo.controller;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.DTO.bike.CreateBikeRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Bikes", description = "Controller to managing bikes endpoints")
public interface BikeController {
    
    @Operation(
        summary = "Get Bike",
        description = "Finds a Bike in the database",
        parameters = {
            @Parameter(name = "bikeId", description = "Id of the bike to be found")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in finding bike"),
            @ApiResponse(responseCode = "404", description = "Did not found the bike in the database")
        }
    )
    public ResponseEntity<Object> getBike(Long bikeId);

    @Operation(
        summary = "Create Bike",
        description = "Creates a Bike in the database",
        parameters = {
            @Parameter(name = "request", description = "DTO with bike's info")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in creating bike"),
            @ApiResponse(responseCode = "404", description = "Did not found the bike in the database")
        }
    )
    public ResponseEntity<Object> createBike(CreateBikeRequest request);

    @Operation(
        summary = "Update Bike",
        description = "Updates a Bike in the database",
        parameters = {
            @Parameter(name = "bikeId", description = "Id of the bike to be found"),
            @Parameter(name = "request", description = "DTO with bike's info")  
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in updating bike"),
            @ApiResponse(responseCode = "404", description = "Did not found the bike in the database")
        }
    )
    public ResponseEntity<Object> updateBike(Long bikeId, CreateBikeRequest request);

    @Operation(
        summary = "Delete Bike",
        description = "Deletes a Bike in the database",
        parameters = {
            @Parameter(name = "bikeId", description = "Id of the bike to be found")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in deleting bike"),
            @ApiResponse(responseCode = "404", description = "Did not found the bike in the database")
        }
    )
    public ResponseEntity<Object> deleteBike(Long bikeId);
}
