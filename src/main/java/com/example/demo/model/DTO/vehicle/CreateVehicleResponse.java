package com.example.demo.model.DTO.vehicle;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateVehicleResponse {
    String type;
    String brand;
    String model;
    Integer manufacturingYear;
}
