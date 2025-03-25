package com.teste.testes.model;

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
}
