package com.teste.testes.DTO.bike;

import com.teste.testes.DTO.vehicle.RequestVehicleDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RequestBikeDTO extends RequestVehicleDTO {
    boolean hasElectricStart;
}
