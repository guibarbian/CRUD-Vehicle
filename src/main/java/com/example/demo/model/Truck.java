package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle{

    @Column(name = "cargoCapacity")
    Integer cargoCapacity;
    
}
