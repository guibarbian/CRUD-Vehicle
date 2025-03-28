package com.teste.testes.DTO;

import com.teste.testes.model.Vehicle;
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
