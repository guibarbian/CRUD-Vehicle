package com.teste.testes.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Truck extends Vehicle{

    Integer maxCargo;
}
