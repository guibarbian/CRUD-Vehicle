package com.example.demo.model.DTO.truck;

import com.example.demo.model.DTO.vehicle.CreateVehicleResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateTruckResponse extends CreateVehicleResponse{

    Integer cargoCapacity;
    
}
