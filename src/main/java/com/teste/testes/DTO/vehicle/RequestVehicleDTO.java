package com.teste.testes.DTO.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class RequestVehicleDTO {

    String brand;
    String model;
    Integer manufacturingYear;
}
