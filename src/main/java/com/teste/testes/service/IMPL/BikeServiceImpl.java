package com.teste.testes.service.IMPL;

import com.teste.testes.DTO.bike.RequestBikeDTO;
import com.teste.testes.DTO.bike.ResponseBikeDTO;
import com.teste.testes.model.Bike;
import com.teste.testes.repository.BikeRepository;
import com.teste.testes.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;

    @Override
    public Bike getBike(Long id) {
        Optional<Bike> bike = bikeRepository.findById(id);

        if(bike.isEmpty()){
            throw new RuntimeException("Bike not found");
        }

        return bike.get();
    }

    @Override
    public ResponseBikeDTO createBike(RequestBikeDTO requestBikeDTO){
        Bike newBike = Bike.builder()
                            .type("Bike")
                            .model(requestBikeDTO.getModel())
                            .brand(requestBikeDTO.getBrand())
                            .manufacturingYear(requestBikeDTO.getManufacturingYear())
                            .hasElectricStart(requestBikeDTO.isHasElectricStart())
                            .build();

        bikeRepository.save(newBike);

        return ResponseBikeDTO.builder()
                                .id(newBike.getId())
                                .type(newBike.getType())
                                .model(newBike.getModel())
                                .brand(newBike.getBrand())
                                .manufacturingYear(newBike.getManufacturingYear())
                                .hasElectricStart(newBike.isHasElectricStart())
                                .build();
    }

    @Override
    public ResponseBikeDTO updateBike(Long bikeId, RequestBikeDTO requestBikeDTO) {
        Optional<Bike> bike = bikeRepository.findById(bikeId);

        if (bike.isEmpty()) {
            throw new RuntimeException("Bike not found");
        }

        Bike updatedBike = Bike.builder()
                .id(bikeId)
                .type("Bike")
                .brand(requestBikeDTO.getBrand())
                .model(requestBikeDTO.getModel())
                .manufacturingYear(requestBikeDTO.getManufacturingYear())
                .hasElectricStart(requestBikeDTO.isHasElectricStart())
                .build();

        bikeRepository.save(updatedBike);

        return ResponseBikeDTO.builder()
                .id(updatedBike.getId())
                .type(updatedBike.getType())
                .brand(updatedBike.getBrand())
                .model(updatedBike.getModel())
                .manufacturingYear(updatedBike.getManufacturingYear())
                .hasElectricStart(updatedBike.isHasElectricStart())
                .build();
    }

    @Override
    public String deleteBike(Long bikeId) {
        Optional<Bike> bike = bikeRepository.findById(bikeId);

        if(bike.isEmpty()) {
            throw new RuntimeException("Bike not found");
        }

        bikeRepository.deleteById(bikeId);

        return "Bike deleted";
    }
}
