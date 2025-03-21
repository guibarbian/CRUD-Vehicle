package com.example.demo.model.DTO.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class CreateVehicleResponse {
    String type;
    String brand;
    String model;
    Integer manufacturingYear;
}
