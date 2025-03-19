package com.teste.testes.service;

import com.teste.testes.DTO.bike.RequestBikeDTO;
import com.teste.testes.DTO.bike.ResponseBikeDTO;
import com.teste.testes.model.Bike;

public interface BikeService {

    Bike getBike(Long bikeId);

    ResponseBikeDTO createBike(RequestBikeDTO requestBikeDTO);

    ResponseBikeDTO updateBike(Long bikeId, RequestBikeDTO requestBikeDTO);

    String deleteBike(Long bikeId);

}
