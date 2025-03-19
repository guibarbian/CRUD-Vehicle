package com.teste.testes.DTO.bike;

import com.teste.testes.DTO.vehicle.ResponseVehicleDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResponseBikeDTO extends ResponseVehicleDTO {
    boolean hasElectricStart;
}
