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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public List<ResponseVehicleDTO> findAll(){
        return vehicleRepository.findAll()
                .stream()
                .map(Vehicle::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public ResponseVehicleDTO findById(Long vehicleId){
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if(vehicle.isEmpty()){
            throw new NotFoundException("Vehicle not found");
        }

        return vehicle.get().toDto();
    }

    @Override
    public ResponseVehicleDTO createVehicle(RequestVehicleDTO vehicleDTO){
        Vehicle vehicle = switch(vehicleDTO.getType().toLowerCase()) {
            case "car" -> new Car();
            case "motorcycle" -> new Bike();
            case "truck" -> new Truck();
            default -> throw new BadRequestException("Invalid vehicle type");
        };

        vehicle.setType(vehicleDTO.getType());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setManufacturingYear(vehicleDTO.getManufacturingYear());

        if(vehicle instanceof Car && vehicleDTO.getDoors() != null){
            ((Car) vehicle).setDoors(vehicleDTO.getDoors());
        } else if(vehicle instanceof Bike){
            ((Bike) vehicle).setHasElectricStart(vehicleDTO.isHasElectricStart());
        } else if(vehicle instanceof Truck && vehicleDTO.getMaxCargo() != null){
            ((Truck) vehicle).setMaxCargo(vehicleDTO.getMaxCargo());
        } else{
            throw new BadRequestException("Invalid vehicle type");
        }

        vehicleRepository.save(vehicle);

        return vehicle.toDto();
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

        Vehicle updatedVehicle = switch(vehicle.get().getType().toLowerCase()) {
            case "car" -> new Car();
            case "motorcycle" -> new Bike();
            case "truck" -> new Truck();
            default -> throw new BadRequestException("Invalid vehicle type");
        };

        updatedVehicle.setId(vehicleId);
        updatedVehicle.setType(vehicle.get().getType());
        updatedVehicle.setBrand(vehicleDTO.getBrand());
        updatedVehicle.setModel(vehicleDTO.getModel());
        updatedVehicle.setManufacturingYear(vehicleDTO.getManufacturingYear());

        if(updatedVehicle instanceof Car && vehicleDTO.getDoors() != null){
            ((Car) updatedVehicle).setDoors(vehicleDTO.getDoors());
        } else if(updatedVehicle instanceof Bike){
            ((Bike) updatedVehicle).setHasElectricStart(vehicleDTO.isHasElectricStart());
        } else if(updatedVehicle instanceof Truck && vehicleDTO.getMaxCargo() != null){
            ((Truck) updatedVehicle).setMaxCargo(vehicleDTO.getMaxCargo());
        } else{
            throw new BadRequestException("Invalid vehicle type");
        }

        vehicleRepository.save(updatedVehicle);
        return updatedVehicle.toDto();
    }

    @Override
    public void deleteVehicle(Long vehicleId){
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if(vehicle.isEmpty()){
            throw new NotFoundException("Vehicle not found");
        }

        vehicleRepository.delete(vehicle.get());
    }
}
