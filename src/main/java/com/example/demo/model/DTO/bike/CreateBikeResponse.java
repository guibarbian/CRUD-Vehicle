package com.example.demo.model.DTO.bike;

import com.example.demo.model.DTO.vehicle.CreateVehicleResponse;

import lombok.Data;

@Data
public class CreateBikeResponse extends CreateVehicleResponse{
    
    boolean hasEletricStart;
    
}
