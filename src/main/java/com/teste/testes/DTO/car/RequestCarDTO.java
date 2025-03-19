package com.teste.testes.DTO.car;

import com.teste.testes.DTO.vehicle.RequestVehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCarDTO extends RequestVehicleDTO {
    Integer doors;
}
