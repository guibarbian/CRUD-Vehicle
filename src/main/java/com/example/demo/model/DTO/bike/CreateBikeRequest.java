package com.example.demo.model.DTO.bike;

import com.example.demo.model.DTO.vehicle.CreateVehicleRequest;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@Builder
public class CreateBikeRequest extends CreateVehicleRequest{
    
    boolean hasEletricStart;
    
}
