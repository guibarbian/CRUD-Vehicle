package com.teste.testes.Unit;

import com.teste.testes.DTO.bike.RequestBikeDTO;
import com.teste.testes.DTO.bike.ResponseBikeDTO;
import com.teste.testes.DTO.truck.RequestTruckDTO;
import com.teste.testes.DTO.truck.ResponseTruckDTO;
import com.teste.testes.controller.Impl.TruckControllerImpl;
import com.teste.testes.controller.TruckController;
import com.teste.testes.model.Bike;
import com.teste.testes.model.Truck;
import com.teste.testes.service.IMPL.TruckServiceIMPL;
import com.teste.testes.service.TruckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TruckControllerTest {

    @Mock
    private TruckService truckService;

    @InjectMocks
    private TruckControllerImpl truckController;

    @Test
    void shallReturnTruck(){
        Truck truck = Truck.builder().id((long)1).build();

        when(truckService.getTruck((long)1)).thenReturn(truck);

        ResponseEntity<Truck> response = truckController.getTruck((long)1);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(truck, response.getBody());
        verify(truckService).getTruck((long)1);
    }

    @Test
    void shallCreateTruck(){
        RequestTruckDTO requestDTO = RequestTruckDTO.builder().brand("Volvo").build();
        ResponseTruckDTO responseDTO = ResponseTruckDTO.builder().brand("Volvo").build();

        when(truckService.createTruck(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<ResponseTruckDTO> response = truckController.createTruck(requestDTO);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(responseDTO, response.getBody());
        verify(truckService).createTruck(requestDTO);
    }

    @Test
    void shallUpdateTruck(){
        RequestTruckDTO requestDTO = RequestTruckDTO.builder().brand("Volvo").build();
        ResponseTruckDTO responseDTO = ResponseTruckDTO.builder().brand("Volvo").build();

        when(truckService.updateTruck((long)1, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<ResponseTruckDTO> response = truckController.updateTruck((long)1, requestDTO);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(responseDTO, response.getBody());
        verify(truckService).updateTruck((long)1, requestDTO);
    }

    @Test
    void shallDeleteTruck(){
        when(truckService.deleteTruck((long)1)).thenReturn("Truck deleted");

        ResponseEntity<String> response = truckController.deleteTruck((long)1);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Truck deleted", response.getBody());
        verify(truckService).deleteTruck((long)1);
    }
}
