package com.example.demo.model.DTO.truck;

import com.example.demo.model.DTO.vehicle.CreateVehicleRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateTruckRequest extends CreateVehicleRequest {

    Integer cargoCapacity;
    
}
