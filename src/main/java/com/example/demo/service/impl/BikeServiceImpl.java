package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Bike;
import com.example.demo.model.DTO.bike.CreateBikeRequest;
import com.example.demo.model.DTO.bike.CreateBikeResponse;
import com.example.demo.repository.BikeRepository;
import com.example.demo.service.BikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService{

    private final BikeRepository bikeRepository;

    @Override
    public Bike getBike(Long bikeId){
        if(bikeRepository.findById(bikeId).isEmpty()){
            throw new NotFoundException("Bike not found");
        }

        return bikeRepository.findById(bikeId).get();
    }

    @Override
    public List<Bike> getBikes(){
        return bikeRepository.findAll();
    }

    @Override
    public CreateBikeResponse createNewBike(CreateBikeRequest request){

        bikeRepository.save(Bike.builder()
                                .brand(request.getBrand())
                                .model(request.getModel())
                                .manufacturingYear(request.getManufacturingYear())
                                .hasElectricStart(request.isHasEletricStart())
                                .type("Bike").build());

        return CreateBikeResponse.builder()
                                .brand(request.getBrand())
                                .model(request.getModel())
                                .manufacturingYear(request.getManufacturingYear())
                                .type("Bike")
                                .hasEletricStart(request.isHasEletricStart())
                                .build();
    }

    @Override
    public CreateBikeResponse updateBike(Long bikeId, CreateBikeRequest request){
        if(bikeRepository.findById(bikeId).isEmpty()){
            throw new NotFoundException("Bike not found");
        }

        bikeRepository.save(Bike.builder()
                                .id(bikeId)
                                .brand(request.getBrand())
                                .model(request.getModel())
                                .manufacturingYear(request.getManufacturingYear())
                                .type("Bike")
                                .hasElectricStart(request.isHasEletricStart())
                                .build());

        return CreateBikeResponse.builder()
                                .brand(request.getBrand())
                                .model(request.getModel())
                                .manufacturingYear(request.getManufacturingYear())
                                .type("Bike")
                                .hasEletricStart(request.isHasEletricStart())
                                .build();
    }

    @Override
    public void deleteBike(Long bikeId){
        if(bikeRepository.findById(bikeId).isEmpty()){
            throw new NotFoundException("Bike not found");
        }

        bikeRepository.deleteById(bikeId);
    }
}
