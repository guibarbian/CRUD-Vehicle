package com.teste.testes.Unit;

import com.teste.testes.DTO.car.RequestCarDTO;
import com.teste.testes.DTO.car.ResponseCarDTO;
import com.teste.testes.DTO.truck.RequestTruckDTO;
import com.teste.testes.DTO.truck.ResponseTruckDTO;
import com.teste.testes.model.Car;
import com.teste.testes.model.Truck;
import com.teste.testes.repository.TruckRepository;
import com.teste.testes.service.IMPL.TruckServiceIMPL;
import com.teste.testes.service.TruckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TruckServiceTest {

    @Mock
    private TruckRepository truckRepository;

    @InjectMocks
    private TruckServiceIMPL truckService;

    @Test
    void shallReturnTruck(){
        Truck truck = Truck.builder().id((long)1).build();

        when(truckRepository.findById((long)1)).thenReturn(Optional.of(truck));

        Truck response = truckService.getTruck((long)1);

        assertEquals(response, truck);
        verify(truckRepository, times(1)).findById((long)1);
    }

    @Test
    void shallThrowExceptionWhenTruckNotFound(){
        when(truckRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            truckService.getTruck((long)1);
        } catch (RuntimeException e){
            assertEquals("Truck not found", e.getMessage());
        }
    }

    @Test
    void shallCreateTruck(){
        RequestTruckDTO requestTruckDTO = RequestTruckDTO.builder()
                .brand("Volvo")
                .model("FH 540")
                .manufacturingYear(2023)
                .maxCargo(1000)
                .build();

        ResponseTruckDTO response = truckService.createTruck(requestTruckDTO);

        assertEquals("Truck", response.getType());
        assertEquals("Volvo", response.getBrand());
        verify(truckRepository, times(1)).save(any());
    }

    @Test
    void shallUpdateTruck(){
        Truck truck = Truck.builder().id((long)1).maxCargo(1000).build();

        RequestTruckDTO dto = RequestTruckDTO.builder()
                .maxCargo(2000)
                .build();

        when(truckRepository.findById((long)1)).thenReturn(Optional.of(truck));

        ResponseTruckDTO response = truckService.updateTruck((long)1, dto);

        assertEquals(2000, response.getMaxCargo());
        verify(truckRepository, times(1)).findById((long)1);
        verify(truckRepository, times(1)).save(any());
    }

    @Test
    void shallThrowNotFoundWhenUpdatesTruck(){
        when(truckRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            truckService.updateTruck((long)1, RequestTruckDTO.builder().build());
        } catch (RuntimeException e){
            assertEquals("Truck not found", e.getMessage());
        }
    }

    @Test
    void shallDeleteTruck(){
        Truck truck = Truck.builder().id((long)1).build();

        when(truckRepository.findById((long)1)).thenReturn(Optional.of(truck));

        truckService.deleteTruck((long)1);

        verify(truckRepository, times(1)).findById((long)1);
        verify(truckRepository, times(1)).deleteById((long)1);
    }

    @Test
    void shallThrowNotFoundWhenDeletesTruck(){
        when(truckRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            truckService.deleteTruck((long)1);
        } catch (RuntimeException e){
            assertEquals("Truck not found", e.getMessage());
        }
    }
}
