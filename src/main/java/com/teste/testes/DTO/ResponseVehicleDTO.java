package com.teste.testes.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseVehicleDTO {

    Long id;
    String type;
    String brand;
    String model;
    Integer manufacturingYear;
    Integer doors;
    boolean hasElectricStart;
    Integer maxCargo;
}
