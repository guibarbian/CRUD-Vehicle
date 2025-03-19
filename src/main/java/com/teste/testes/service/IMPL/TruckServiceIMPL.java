package com.teste.testes.service.IMPL;

import com.teste.testes.DTO.truck.RequestTruckDTO;
import com.teste.testes.DTO.truck.ResponseTruckDTO;
import com.teste.testes.model.Truck;
import com.teste.testes.repository.TruckRepository;
import com.teste.testes.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TruckServiceIMPL implements TruckService {

    private final TruckRepository truckRepository;

    @Override
    public Truck getTruck(Long id) {
        Optional<Truck> truck = truckRepository.findById(id);

        if(truck.isEmpty()){
            throw new NotFoundException("Truck not found");
        }

        return truck.get();
    }

    @Override
    public ResponseTruckDTO createTruck(RequestTruckDTO requestTruckDTO) {
        Truck newTruck = Truck.builder()
                .type("Truck")
                .brand(requestTruckDTO.getBrand())
                .model(requestTruckDTO.getModel())
                .manufacturingYear(requestTruckDTO.getManufacturingYear())
                .maxCargo(requestTruckDTO.getMaxCargo()).build();

        truckRepository.save(newTruck);

        return ResponseTruckDTO.builder()
                .id(newTruck.getId())
                .type(newTruck.getType())
                .brand(newTruck.getBrand())
                .model(newTruck.getModel())
                .manufacturingYear(newTruck.getManufacturingYear())
                .maxCargo(newTruck.getMaxCargo()).build();
    }

    @Override
    public ResponseTruckDTO updateTruck(Long truckId, RequestTruckDTO requestTruckDTO){
        Optional<Truck> truck = truckRepository.findById(truckId);

        if(truck.isEmpty()){
            throw new NotFoundException("Truck not found");
        }

        Truck updatedTruck = Truck.builder()
                                .id(truckId)
                                .type("Truck")
                                .brand(requestTruckDTO.getBrand())
                                .model(requestTruckDTO.getModel())
                                .manufacturingYear(requestTruckDTO.getManufacturingYear())
                                .maxCargo(requestTruckDTO.getMaxCargo()).build();

        truckRepository.save(updatedTruck);

        return ResponseTruckDTO.builder()
                                .id(updatedTruck.getId())
                                .type(updatedTruck.getType())
                                .brand(updatedTruck.getBrand())
                                .model(updatedTruck.getModel())
                                .manufacturingYear(updatedTruck.getManufacturingYear())
                                .maxCargo(updatedTruck.getMaxCargo()).build();

    }

    @Override
    public String deleteTruck(Long truckId){
        Optional<Truck> truck = truckRepository.findById(truckId);

        if(truck.isEmpty()){
            throw new NotFoundException("Truck not found");
        }

        truckRepository.deleteById(truckId);

        return "Truck deleted"; }
}
