package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long>{
    
}
