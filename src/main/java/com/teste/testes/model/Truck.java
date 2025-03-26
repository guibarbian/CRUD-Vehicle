package com.teste.testes.model;

import com.teste.testes.DTO.ResponseVehicleDTO;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.coyote.Response;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Truck extends Vehicle{

    Integer maxCargo;

    @Override
    public ResponseVehicleDTO toDto(){
        return ResponseVehicleDTO.builder()
                .id(this.getId())
                .type(this.getType())
                .brand(this.getBrand())
                .model(this.getModel())
                .manufacturingYear(this.getManufacturingYear())
                .maxCargo(this.getMaxCargo())
                .build();
    }
}
