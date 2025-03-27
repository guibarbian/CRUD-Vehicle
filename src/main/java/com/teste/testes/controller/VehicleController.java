package com.teste.testes.controller;

import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.DTO.ResponseVehicleDTO;
import com.teste.testes.model.Vehicle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface VehicleController {

    @Operation(
            summary = "Get all vehicles",
            description = "Gets all the vehicles in the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succeeded in returning a list of vehicles")
        }
    )
    ResponseEntity<List<ResponseVehicleDTO>> getAllVehicles();

    @Operation(
            summary = "Get vehicle by Id",
            description = "Gets a specific vehicle by its id",
            parameters = {
              @Parameter(name = "id", description = "Id of the vehicle")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succeeded in returning a vehicle"),
                    @ApiResponse(responseCode = "404", description = "Id didn't belong to any vehicle")
            }
    )
    ResponseEntity<ResponseVehicleDTO> getVehicleById(Long id);

    @Operation(
            summary = "Create a vehicle",
            description = "Creates a vehicle using the request body and saves it in the database",
            parameters = {
                    @Parameter(name = "request", description = "Request body")
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Succeeded in returning a vehicle"),
                    @ApiResponse(responseCode = "400", description = "Invalid request body")
            }
    )
    ResponseEntity<ResponseVehicleDTO> createVehicle(RequestVehicleDTO request);

    @Operation(
            summary = "Updates a vehicle",
            description = "Updates a vehicle and saves it over the old one in the database",
            parameters = {
                    @Parameter(name = "id", description = "Id of the vehicle"),
                    @Parameter(name = "request", description = "Request body")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succeeded in updating vehicle"),
                    @ApiResponse(responseCode = "400", description = "Invalid request body"),
                    @ApiResponse(responseCode = "404", description = "Id didn't belong to any vehicle")
            }
    )
    ResponseEntity<ResponseVehicleDTO> updateVehicle(Long id, RequestVehicleDTO request);

    @Operation(
            summary = "Deletes a vehicle",
            description = "Deletes a vehicle from the database",
            parameters = {
                    @Parameter(name = "id", description = "Id of the vehicle")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succeeded in deleting vehicle"),
                    @ApiResponse(responseCode = "404", description = "Id didn't belong to any vehicle")
            }
    )
    ResponseEntity<String> deleteVehicle(Long id);
}
