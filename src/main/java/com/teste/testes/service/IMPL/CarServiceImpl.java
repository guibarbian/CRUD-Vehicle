package com.teste.testes.service.IMPL;

import com.teste.testes.DTO.car.RequestCarDTO;
import com.teste.testes.DTO.car.ResponseCarDTO;
import com.teste.testes.model.Car;
import com.teste.testes.repository.CarRepository;
import com.teste.testes.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car getCar(Long carId){
        Optional<Car> car = carRepository.findById(carId);

        if(car.isEmpty()){
            throw new NotFoundException("Car not found");
        }

        return car.get();
    }

    @Override
    public ResponseCarDTO createCar(RequestCarDTO dto) {
        Car newCar = Car.builder()
                        .type("Car")
                        .brand(dto.getBrand())
                        .model(dto.getModel())
                        .manufacturingYear(dto.getManufacturingYear())
                        .doors(dto.getDoors())
                        .build();

        carRepository.save(newCar);

        return ResponseCarDTO.builder()
                                .id(newCar.getId())
                                .type(newCar.getType())
                                .brand(newCar.getBrand())
                                .model(newCar.getModel())
                                .manufacturingYear(newCar.getManufacturingYear())
                                .doors(newCar.getDoors())
                                .build();
    }

    @Override
    public ResponseCarDTO updateCar(Long carId, RequestCarDTO dto) {

        if(carRepository.findById(carId).isEmpty()){
            throw new NotFoundException("Car not found");
        }

        Car updatedCar = Car.builder()
                            .id(carId)
                            .type("Car")
                            .brand(dto.getBrand())
                            .model(dto.getModel())
                            .manufacturingYear(dto.getManufacturingYear())
                            .doors(dto.getDoors())
                            .build();

        carRepository.save(updatedCar);

        return ResponseCarDTO.builder()
                                .id(updatedCar.getId())
                                .type(updatedCar.getType())
                                .brand(updatedCar.getBrand())
                                .model(updatedCar.getModel())
                                .manufacturingYear(updatedCar.getManufacturingYear())
                                .doors(updatedCar.getDoors())
                                .build();
    }

    @Override
    public String deleteCar(Long carId){
        if(carRepository.findById(carId).isEmpty()){
            throw new NotFoundException("Car not found");
        }

        carRepository.deleteById(carId);

        return "Car deleted";
    }
}
