package com.teste.testes.service.IMPL;

import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.DTO.ResponseVehicleDTO;
import com.teste.testes.exception.BadRequestException;
import com.teste.testes.exception.NotFoundException;
import com.teste.testes.model.Bike;
import com.teste.testes.model.Car;
import com.teste.testes.model.Truck;
import com.teste.testes.model.Vehicle;
import com.teste.testes.repository.VehicleRepository;
import com.teste.testes.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findById(Long vehicleId){
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if(vehicle.isEmpty()){
            throw new NotFoundException("Vehicle not found");
        }

        return vehicle.get();
    }

    @Override
    public ResponseVehicleDTO createVehicle(RequestVehicleDTO vehicleDTO){
        return switch (vehicleDTO.getType().toLowerCase()) {
            case "car" -> createCar(vehicleDTO);
            case "motorcycle" -> createMotorcycle(vehicleDTO);
            case "truck" -> createTruck(vehicleDTO);
            default -> throw new BadRequestException("Invalid vehicle type");
        };
    }

    @Override
    public ResponseVehicleDTO updateVehicle(Long vehicleId, RequestVehicleDTO vehicleDTO){
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if(vehicle.isEmpty()){
            throw new NotFoundException("Vehicle not found");
        }

        if(!Objects.equals(vehicle.get().getType().toLowerCase(), vehicleDTO.getType().toLowerCase())){
            throw new BadRequestException("Vehicle type cannot be changed");
        }

        return switch (vehicle.get().getType().toLowerCase()) {
            case "car" -> updateCar(vehicleId, vehicleDTO);
            case "motorcycle" -> updateMotorcycle(vehicleId, vehicleDTO);
            case "truck" -> updateTruck(vehicleId, vehicleDTO);
            default -> throw new BadRequestException("Invalid vehicle type");
        };
    }

    @Override
    public void deleteVehicle(Long vehicleId){
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if(vehicle.isEmpty()){
            throw new NotFoundException("Vehicle not found");
        }

        vehicleRepository.delete(vehicle.get());
    }


    private ResponseVehicleDTO createCar(RequestVehicleDTO vehicleDTO){
        Car car = Car.builder()
                .type(vehicleDTO.getType())
                .brand(vehicleDTO.getBrand())
                .model(vehicleDTO.getModel())
                .manufacturingYear(vehicleDTO.getManufacturingYear())
                .doors(vehicleDTO.getDoors())
                .build();

        vehicleRepository.save(car);

        return ResponseVehicleDTO.builder()
                .id(car.getId())
                .type(car.getType())
                .brand(car.getBrand())
                .model(car.getModel())
                .manufacturingYear(car.getManufacturingYear())
                .doors(car.getDoors())
                .build();
    }

    private ResponseVehicleDTO updateCar(Long carId, RequestVehicleDTO vehicleDTO){
        Car car = Car.builder()
                .id(carId)
                .type(vehicleDTO.getType())
                .brand(vehicleDTO.getBrand())
                .model(vehicleDTO.getModel())
                .manufacturingYear(vehicleDTO.getManufacturingYear())
                .doors(vehicleDTO.getDoors())
                .build();

        vehicleRepository.save(car);

        return ResponseVehicleDTO.builder()
                .id(car.getId())
                .type(car.getType())
                .brand(car.getBrand())
                .model(car.getModel())
                .manufacturingYear(car.getManufacturingYear())
                .doors(car.getDoors())
                .build();
    }

    private ResponseVehicleDTO createMotorcycle(RequestVehicleDTO vehicleDTO){
        Bike bike = Bike.builder()
                .type(vehicleDTO.getType())
                .brand(vehicleDTO.getBrand())
                .model(vehicleDTO.getModel())
                .manufacturingYear(vehicleDTO.getManufacturingYear())
                .hasElectricStart(vehicleDTO.isHasElectricStart())
                .build();

        vehicleRepository.save(bike);

        return ResponseVehicleDTO.builder()
                .id(bike.getId())
                .type(bike.getType())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .manufacturingYear(bike.getManufacturingYear())
                .hasElectricStart(bike.isHasElectricStart())
                .build();
    }

    private ResponseVehicleDTO updateMotorcycle(Long bikeId, RequestVehicleDTO vehicleDTO){
        Bike bike = Bike.builder()
                .id(bikeId)
                .type(vehicleDTO.getType())
                .brand(vehicleDTO.getBrand())
                .model(vehicleDTO.getModel())
                .manufacturingYear(vehicleDTO.getManufacturingYear())
                .hasElectricStart(vehicleDTO.isHasElectricStart())
                .build();

        vehicleRepository.save(bike);

        return ResponseVehicleDTO.builder()
                .id(bike.getId())
                .type(bike.getType())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .manufacturingYear(bike.getManufacturingYear())
                .hasElectricStart(bike.isHasElectricStart())
                .build();
    }

    private ResponseVehicleDTO createTruck(RequestVehicleDTO vehicleDTO){
        Truck truck = Truck.builder()
                .type(vehicleDTO.getType())
                .brand(vehicleDTO.getBrand())
                .model(vehicleDTO.getModel())
                .manufacturingYear(vehicleDTO.getManufacturingYear())
                .maxCargo(vehicleDTO.getMaxCargo())
                .build();

        vehicleRepository.save(truck);

        return ResponseVehicleDTO.builder()
                .id(truck.getId())
                .type(truck.getType())
                .brand(truck.getBrand())
                .model(truck.getModel())
                .manufacturingYear(truck.getManufacturingYear())
                .maxCargo(truck.getMaxCargo())
                .build();
    }

    private ResponseVehicleDTO updateTruck(Long truckId, RequestVehicleDTO vehicleDTO){
        Truck truck = Truck.builder()
                .id(truckId)
                .type(vehicleDTO.getType())
                .brand(vehicleDTO.getBrand())
                .model(vehicleDTO.getModel())
                .manufacturingYear(vehicleDTO.getManufacturingYear())
                .maxCargo(vehicleDTO.getMaxCargo())
                .build();

        vehicleRepository.save(truck);

        return ResponseVehicleDTO.builder()
                .id(truck.getId())
                .type(truck.getType())
                .brand(truck.getBrand())
                .model(truck.getModel())
                .manufacturingYear(truck.getManufacturingYear())
                .maxCargo(truck.getMaxCargo())
                .build();
    }

}
