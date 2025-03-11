package com.example.demo.model.DTO.bike;

import com.example.demo.model.DTO.vehicle.CreateVehicleResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateBikeResponse extends CreateVehicleResponse{
    
    boolean hasEletricStart;
    
}
