package com.example.demo.model.DTO.car;

import com.example.demo.model.DTO.vehicle.CreateVehicleRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateCarRequest extends CreateVehicleRequest{

    Integer numberOfDoors;
    
}
