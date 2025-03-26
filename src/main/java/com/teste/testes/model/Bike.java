package com.teste.testes.model;

import com.teste.testes.DTO.ResponseVehicleDTO;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Bike extends Vehicle{
    boolean hasElectricStart;

    @Override
    public ResponseVehicleDTO toDto(){
        return ResponseVehicleDTO.builder()
                .id(this.getId())
                .type(this.getType())
                .brand(this.getBrand())
                .model(this.getModel())
                .manufacturingYear(this.getManufacturingYear())
                .hasElectricStart(this.hasElectricStart)
                .build();
    }
}
