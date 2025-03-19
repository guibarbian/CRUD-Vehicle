package com.teste.testes.DTO.vehicle;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class ResponseVehicleDTO {

    Long id;
    String type;
    String brand;
    String model;
    Integer manufacturingYear;
}
