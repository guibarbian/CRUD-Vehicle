package com.teste.testes.model;

import com.teste.testes.DTO.ResponseVehicleDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Car extends Vehicle{
    @Column(name = "doors")
    Integer doors;

    @Override
    public ResponseVehicleDTO toDto(){
        return ResponseVehicleDTO.builder()
                .id(this.getId())
                .type(this.getType())
                .brand(this.getBrand())
                .model(this.getModel())
                .manufacturingYear(this.getManufacturingYear())
                .doors(this.doors)
                .build();
    }
}
