package com.example.demo.model.DTO.bike;

import com.example.demo.model.DTO.vehicle.CreateVehicleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateBikeRequest extends CreateVehicleRequest{
    
    boolean hasEletricStart;
    
}
