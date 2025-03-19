package com.teste.testes.controller;

import com.teste.testes.DTO.bike.RequestBikeDTO;
import com.teste.testes.DTO.bike.ResponseBikeDTO;
import com.teste.testes.model.Bike;
import org.springframework.http.ResponseEntity;

public interface BikeController {

    ResponseEntity<Bike> getBike(Long bikeId);

    ResponseEntity<ResponseBikeDTO> createBike(RequestBikeDTO requestBikeDTO);

    ResponseEntity<ResponseBikeDTO> updateBike(Long bikeId, RequestBikeDTO requestBikeDTO);

    ResponseEntity<String> deleteBike(Long bikeId);
}
