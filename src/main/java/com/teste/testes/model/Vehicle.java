package com.teste.testes.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.teste.testes.DTO.ResponseVehicleDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicles")
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "type", updatable=false)
    String type;

    @Column(name = "brand")
    String brand;

    @Column(name = "model")
    String model;

    @Column(name = "manufacturingYear")
    Integer manufacturingYear;

    public abstract ResponseVehicleDTO toDto();
    
}
