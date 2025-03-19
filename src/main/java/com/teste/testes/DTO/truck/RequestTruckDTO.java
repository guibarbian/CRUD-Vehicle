package com.teste.testes.DTO.truck;

import com.teste.testes.DTO.vehicle.RequestVehicleDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class RequestTruckDTO extends RequestVehicleDTO {

    Integer maxCargo;
}
