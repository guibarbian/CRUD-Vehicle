package com.teste.testes.Unit;

import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.DTO.ResponseVehicleDTO;
import com.teste.testes.model.Bike;
import com.teste.testes.model.Car;
import com.teste.testes.model.Truck;
import com.teste.testes.model.Vehicle;
import com.teste.testes.repository.VehicleRepository;
import com.teste.testes.service.IMPL.VehicleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    private ArgumentCaptor<Car> carCaptor;

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
                .doors(0).build();

        Car car = Car.builder()
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .doors(dto.getDoors()).build();

        when(vehicleRepository.save(car)).thenReturn(carCaptor.capture());

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

        Bike savedBike = Bike.builder()
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .hasElectricStart(dto.isHasElectricStart()).build();

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(savedBike);

        ResponseVehicleDTO response = vehicleService.createVehicle(dto);

        assertEquals("motorcycle", response.getType().toLowerCase());
        assertTrue(response.isHasElectricStart());
        assertNull(response.getDoors());
        assertNull(response.getMaxCargo());
        verify(vehicleRepository, times(1)).save(any(Bike.class));
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

        Truck savedTruck = Truck.builder()
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .maxCargo(dto.getMaxCargo())
                .build();

        when(vehicleRepository.save(any(Truck.class))).thenReturn(savedTruck);

        ResponseVehicleDTO response = vehicleService.createVehicle(dto);

        assertEquals("truck", response.getType().toLowerCase());
        assertEquals(10000, response.getMaxCargo());
        assertNull(response.getDoors());
        assertFalse(response.isHasElectricStart());
        verify(vehicleRepository, times(1)).save(any(Truck.class));
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
        Car car = Car.builder().type("car").doors(2).build();

        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("car")
                .brand("Ford")
                .model("Ka")
                .manufacturingYear(2020)
                .doors(4).build();

        Car savedCar = Car.builder()
                        .type(dto.getType())
                        .brand(dto.getBrand())
                        .model(dto.getModel())
                        .manufacturingYear(dto.getManufacturingYear())
                        .doors(dto.getDoors()).build();

        when(vehicleRepository.findById(car.getId())).thenReturn(Optional.of(car));
        when(vehicleRepository.save(any(Car.class))).thenReturn(savedCar);

        ResponseVehicleDTO response = vehicleService.updateVehicle(car.getId(), dto);

        assertEquals("Ford", response.getBrand());
        assertEquals(4, response.getDoors());
        assertNull(response.getMaxCargo());
        assertFalse(response.isHasElectricStart());
        verify(vehicleRepository, times(1)).findById(any());
        verify(vehicleRepository, times(1)).save(any());
    }

    @Test
    void shallUpdateBike(){
        Bike bike = Bike.builder().type("Motorcycle").hasElectricStart(false).build();

        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("motorcycle")
                .brand("Honda")
                .model("CBR")
                .manufacturingYear(2020)
                .hasElectricStart(true).build();

        Bike savedBike = Bike.builder()
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .hasElectricStart(dto.isHasElectricStart()).build();

        when(vehicleRepository.save(any(Bike.class))).thenReturn(savedBike);
        when(vehicleRepository.findById(bike.getId())).thenReturn(Optional.of(bike));

        ResponseVehicleDTO response = vehicleService.updateVehicle(bike.getId(), dto);

        assertEquals("Honda", response.getBrand());
        assertTrue(response.isHasElectricStart());
        assertNull(response.getDoors());
        assertNull(response.getMaxCargo());
        verify(vehicleRepository, times(1)).findById(any());
        verify(vehicleRepository, times(1)).save(any());
    }

    @Test
    void shallUpdateTruck(){
        Truck truck = Truck.builder()
                .type("Truck")
                .brand("Scania")
                .model("R440")
                .manufacturingYear(2020)
                .maxCargo(1000).build();

        RequestVehicleDTO dto = RequestVehicleDTO.builder()
                .type("Truck")
                .brand("Scania")
                .model("R440")
                .manufacturingYear(2020)
                .maxCargo(10000).build();

        Truck updatedTruck = Truck.builder()
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .manufacturingYear(dto.getManufacturingYear())
                .maxCargo(dto.getMaxCargo()).build();

        when(vehicleRepository.findById(truck.getId())).thenReturn(Optional.of(truck));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(updatedTruck);

        ResponseVehicleDTO response = vehicleService.updateVehicle(truck.getId(), dto);

        assertEquals("Scania", response.getBrand());
        assertEquals(10000, response.getMaxCargo());
        assertNull(response.getDoors());
        assertFalse(response.isHasElectricStart());
        verify(vehicleRepository, times(1)).findById(any());
        verify(vehicleRepository, times(1)).save(any());
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
            verify(vehicleRepository, times(1)).findById(any());
            verify(vehicleRepository, times(0)).save(any());
        }
    }

    @Test
    void shallDeleteVehicle(){
        Car car = Car.builder().doors(4).build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(car));

        vehicleService.deleteVehicle(1L);

        verify(vehicleRepository, times(1)).findById(any());
        verify(vehicleRepository, times(1)).delete(any());
    }

    @Test
    void shallThrowNotFoundWhenDeletes(){
        try{
            vehicleService.deleteVehicle(1L);
            fail("Exception not thrown");
        } catch(Exception e){
            assertEquals("Vehicle not found", e.getMessage());
            verify(vehicleRepository, times(1)).findById(any());
            verify(vehicleRepository, times(0)).delete(any());
        }
    }
}
