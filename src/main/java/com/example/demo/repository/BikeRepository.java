package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long>{
    
}
