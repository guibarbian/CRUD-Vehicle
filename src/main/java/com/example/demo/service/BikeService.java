package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Bike;
import com.example.demo.model.DTO.bike.CreateBikeRequest;
import com.example.demo.model.DTO.bike.CreateBikeResponse;

public interface BikeService {
    
    Bike getBike();

    List<Bike> getBikes();

    CreateBikeResponse createNewBike(CreateBikeRequest requestDto);

    CreateBikeResponse updateBike(Long bikeId, CreateBikeRequest requestDto);

    void deleteBike(Long bikeId);
}
