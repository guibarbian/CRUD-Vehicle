package com.teste.testes.Unit;

import static org.junit.jupiter.api.Assertions.*;

import com.teste.testes.DTO.bike.RequestBikeDTO;
import com.teste.testes.DTO.bike.ResponseBikeDTO;
import com.teste.testes.controller.Impl.BikeControllerImpl;
import com.teste.testes.model.Bike;
import com.teste.testes.service.BikeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BikeControllerTest {

    @Mock
    private BikeService bikeService;

    @InjectMocks
    private BikeControllerImpl bikeController;

    @Test
    void shallReturnBike(){
        Bike bike = Bike.builder().id((long)1).build();

        when(bikeService.getBike((long)1)).thenReturn(bike);

        ResponseEntity<Bike> response = bikeController.getBike((long)1);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(bike, response.getBody());
        verify(bikeService).getBike((long)1);
    }

    @Test
    void shallCreateBike(){
        RequestBikeDTO requestDTO = RequestBikeDTO.builder().brand("Kawasaki").build();
        ResponseBikeDTO responseDTO = ResponseBikeDTO.builder().brand("Kawasaki").build();

        when(bikeService.createBike(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<ResponseBikeDTO> response = bikeController.createBike(requestDTO);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(responseDTO, response.getBody());
        verify(bikeService).createBike(requestDTO);
    }

    @Test
    void shallUpdateBike(){
        RequestBikeDTO requestDTO = RequestBikeDTO.builder().brand("Kawasaki").build();
        ResponseBikeDTO responseDTO = ResponseBikeDTO.builder().brand("Kawasaki").build();

        when(bikeService.updateBike((long)1, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<ResponseBikeDTO> response = bikeController.updateBike((long)1, requestDTO);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(responseDTO, response.getBody());
        verify(bikeService).updateBike((long)1, requestDTO);
    }

    @Test
    void shallDeleteBike(){
        when(bikeService.deleteBike((long)1)).thenReturn("Bike deleted");

        ResponseEntity<String> response = bikeController.deleteBike((long)1);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Bike deleted", response.getBody());
        verify(bikeService).deleteBike((long)1);
    }
}
