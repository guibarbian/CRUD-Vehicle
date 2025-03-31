package com.teste.testes.Unit;

import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.DTO.ResponseVehicleDTO;
import com.teste.testes.model.Bike;
import com.teste.testes.model.Car;
import com.teste.testes.model.Truck;
import com.teste.testes.repository.VehicleRepository;
import com.teste.testes.service.IMPL.VehicleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Test
    void shallReturnVehicles(){
        Car car = Car.builder().doors(4).build();
        Bike bike = Bike.builder().hasElectricStart(true).build();
        Truck truck = Truck.builder().maxCargo(1000).build();

        when(vehicleRepository.findAll()).thenReturn(List.of(car, bike, truck));

        List<ResponseVehicleDTO> response = vehicleService.findAll();

        assertEquals(3, response.size());
        assertEquals(4, response.get(0).getDoors());
        assertTrue(response.get(1).isHasElectricStart());
        assertEquals(1000, response.get(2).getMaxCargo());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void shallReturnCar() {
        Car car = Car.builder().doors(4).build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(car));

        ResponseVehicleDTO response = vehicleService.findById(1L);

        assertEquals(car.getId(), response.getId());
        assertEquals(car.getType(), response.getType());
        assertEquals(car.getDoors(), response.getDoors());
    }

    @Test
    void shallCreateCar(){
        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("car")
                .brand("Ford")
                .model("Ka")
                .manufacturingYear(2020)
                .doors(4).build();

        Car car = Car.builder()
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .doors(dto.getDoors()).build();

        Car savedCar = Car.builder()
                .id(car.getId())
                .type(car.getType())
                .brand(car.getBrand())
                .model(car.getModel())
                .manufacturingYear(car.getManufacturingYear())
                .doors(car.getDoors()).build();

        when(vehicleRepository.save(car)).thenReturn(savedCar);

        ResponseVehicleDTO response = vehicleService.createVehicle(dto);

        assertNotNull(car.getType());
        assertEquals("car", response.getType().toLowerCase());
        assertEquals(4, response.getDoors());
        assertNull(response.getMaxCargo());
        assertFalse(response.isHasElectricStart());
        verify(vehicleRepository, times(1)).save(car);
    }

    @Test
    void shallCreateBike(){
        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("Motorcycle")
                .brand("Honda")
                .model("CBR")
                .manufacturingYear(2020)
                .hasElectricStart(true).build();

        Bike bike = Bike.builder()
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .hasElectricStart(dto.isHasElectricStart()).build();

        Bike savedBike = Bike.builder()
                .id(bike.getId())
                .type(bike.getType())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .manufacturingYear(bike.getManufacturingYear())
                .hasElectricStart(bike.isHasElectricStart()).build();


        when(vehicleRepository.save(bike)).thenReturn(savedBike);

        ResponseVehicleDTO response = vehicleService.createVehicle(dto);

        assertEquals("motorcycle", response.getType().toLowerCase());
        assertTrue(response.isHasElectricStart());
        assertNull(response.getDoors());
        assertNull(response.getMaxCargo());
        verify(vehicleRepository, times(1)).save(bike);
    }

    @Test
    void shallCreateTruck(){
        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("Truck")
                .brand("Scania")
                .model("R440")
                .manufacturingYear(2020)
                .maxCargo(10000)
                .build();

        Truck truck = Truck.builder()
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .maxCargo(dto.getMaxCargo()).build();

        Truck savedTruck = Truck.builder()
                .id(truck.getId())
                .type(truck.getType())
                .brand(truck.getBrand())
                .model(truck.getModel())
                .manufacturingYear(truck.getManufacturingYear())
                .maxCargo(truck.getMaxCargo()).build();

        when(vehicleRepository.save(truck)).thenReturn(savedTruck);

        ResponseVehicleDTO response = vehicleService.createVehicle(dto);

        assertEquals("truck", response.getType().toLowerCase());
        assertEquals(10000, response.getMaxCargo());
        assertNull(response.getDoors());
        assertFalse(response.isHasElectricStart());
        verify(vehicleRepository, times(1)).save(truck);
    }

    @Test
    void shallThrowBadRequestWhenTypeIsInvalid(){
        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("Invalid")
                .brand("Scania")
                .model("R440")
                .manufacturingYear(2020)
                .maxCargo(10000)
                .build();

        try{
            vehicleService.createVehicle(dto);
            fail("Exception not thrown");
        } catch(Exception e){
            assertEquals("Invalid vehicle type", e.getMessage());
            verify(vehicleRepository, times(0)).save(any());
        }
    }

    @Test
    void shallUpdateCar(){
        Car existingCar = Car.builder()
                .id(1L)
                .type("car")
                .brand("Ford")
                .model("Ka")
                .manufacturingYear(2020)
                .doors(2).build();

        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("car")
                .brand("Ford")
                .model("Ka")
                .manufacturingYear(2020)
                .doors(4).build();

        Car car = Car.builder()
                .id(existingCar.getId())
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .doors(dto.getDoors()).build();

        Car savedCar = Car.builder()
                .id(car.getId())
                .type(car.getType())
                .brand(car.getBrand())
                .model(car.getModel())
                .manufacturingYear(car.getManufacturingYear())
                .doors(car.getDoors()).build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        when(vehicleRepository.save(car)).thenReturn(savedCar);

        ResponseVehicleDTO response = vehicleService.updateVehicle(existingCar.getId(), dto);

        assertEquals("Ford", response.getBrand());
        assertEquals(4, response.getDoors());
        assertNull(response.getMaxCargo());
        assertFalse(response.isHasElectricStart());
        verify(vehicleRepository, times(1)).findById(car.getId());
        verify(vehicleRepository, times(1)).save(car);
    }

    @Test
    void shallUpdateBike(){
        Bike existingBike = Bike.builder()
                .id(1L)
                .type("motorcycle")
                .brand("Honda")
                .model("CBR")
                .manufacturingYear(2020)
                .hasElectricStart(false).build();

        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("motorcycle")
                .brand("Honda")
                .model("CBR")
                .manufacturingYear(2020)
                .hasElectricStart(true).build();

        Bike bike = Bike.builder()
                .id(existingBike.getId())
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .hasElectricStart(dto.isHasElectricStart()).build();

        Bike savedBike = Bike.builder()
                .id(bike.getId())
                .type(bike.getType())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .manufacturingYear(bike.getManufacturingYear())
                .hasElectricStart(bike.isHasElectricStart()).build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(existingBike));
        when(vehicleRepository.save(bike)).thenReturn(savedBike);

        ResponseVehicleDTO response = vehicleService.updateVehicle(bike.getId(), dto);

        assertEquals("Honda", response.getBrand());
        assertTrue(response.isHasElectricStart());
        assertNull(response.getDoors());
        assertNull(response.getMaxCargo());
        verify(vehicleRepository, times(1)).findById(existingBike.getId());
        verify(vehicleRepository, times(1)).save(bike);
    }

    @Test
    void shallUpdateTruck(){
        Truck existingTruck = Truck.builder()
                .id(1L)
                .type("truck")
                .brand("Scania")
                .model("R440")
                .manufacturingYear(2020)
                .maxCargo(1000).build();

        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("truck")
                .brand("Scania")
                .model("R440")
                .manufacturingYear(2020)
                .maxCargo(10000).build();

        Truck truck = Truck.builder()
                .id(existingTruck.getId())
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .maxCargo(dto.getMaxCargo()).build();

        Truck savedTruck = Truck.builder()
                .id(truck.getId())
                .type(truck.getType())
                .brand(truck.getBrand())
                .model(truck.getModel())
                .manufacturingYear(truck.getManufacturingYear())
                .maxCargo(truck.getMaxCargo()).build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(existingTruck));
        when(vehicleRepository.save(truck)).thenReturn(savedTruck);

        ResponseVehicleDTO response = vehicleService.updateVehicle(truck.getId(), dto);

        assertEquals("Scania", response.getBrand());
        assertEquals(10000, response.getMaxCargo());
        assertNull(response.getDoors());
        assertFalse(response.isHasElectricStart());
        verify(vehicleRepository, times(1)).findById(truck.getId());
        verify(vehicleRepository, times(1)).save(truck);
    }

    @Test
    void shallThrowBadRequestWhenTypeIsInvalidWhenUpdates(){
        Truck truck = Truck.builder().type("Truck").maxCargo(1000).build();

        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("Invalid")
                .brand("Scania")
                .model("R440")
                .manufacturingYear(2020)
                .maxCargo(10000)
                .build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(truck));

        try{
            vehicleService.updateVehicle(1L, dto);
            fail("Exception not thrown");
        } catch(Exception e){
            assertEquals("Vehicle type cannot be changed", e.getMessage());
            verify(vehicleRepository, times(0)).save(any());
        }
    }

    @Test
    void shallThrowNotFoundWhenUpdates(){
        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("car")
                .brand("Ford")
                .model("Ka")
                .manufacturingYear(2020)
                .doors(4)
                .build();

        try{
            vehicleService.updateVehicle(1L, dto);
            fail("Exception not thrown");
        } catch(Exception e){
            assertEquals("Vehicle not found", e.getMessage());
            verify(vehicleRepository, times(1)).findById(1L);
            verify(vehicleRepository, times(0)).save(any());
        }
    }

    @Test
    void shallDeleteVehicle(){
        Car car = Car.builder().id(1L).doors(4).build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(car));

        vehicleService.deleteVehicle(1L);

        verify(vehicleRepository, times(1)).findById(car.getId());
        verify(vehicleRepository, times(1)).delete(car);
    }

    @Test
    void shallThrowNotFoundWhenDeletes(){
        try{
            vehicleService.deleteVehicle(1L);
            fail("Exception not thrown");
        } catch(Exception e){
            assertEquals("Vehicle not found", e.getMessage());
            verify(vehicleRepository, times(1)).findById(1L);
            verify(vehicleRepository, times(0)).delete(any());
        }
    }
}
