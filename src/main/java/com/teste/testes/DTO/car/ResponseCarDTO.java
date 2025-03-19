package com.teste.testes.DTO.car;

import com.teste.testes.DTO.vehicle.ResponseVehicleDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResponseCarDTO extends ResponseVehicleDTO {
    Integer doors;
}
