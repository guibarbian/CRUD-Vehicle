package com.teste.testes.DTO.truck;

import com.teste.testes.DTO.car.ResponseCarDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ResponseTruckDTO extends ResponseCarDTO {

    Integer maxCargo;
}
