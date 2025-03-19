package com.teste.testes.Unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.teste.testes.DTO.bike.RequestBikeDTO;
import com.teste.testes.DTO.bike.ResponseBikeDTO;
import com.teste.testes.model.Bike;
import com.teste.testes.repository.BikeRepository;
import com.teste.testes.service.IMPL.BikeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private BikeServiceImpl bikeService;

    @Test
    void shallReturnBike(){
        Bike bike = Bike.builder().id((long)1).build();

        when(bikeRepository.findById((long)1)).thenReturn(Optional.of(bike));

        Bike response = bikeService.getBike((long)1);

        assertEquals(response, bike);
        verify(bikeRepository, times(1)).findById((long)1);
    }

    @Test
    void shallThrowExceptionWhenBikeNotFound(){
        when(bikeRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            bikeService.getBike((long)1);
            fail("Exception not thrown");
        } catch (RuntimeException e){
            assertEquals("Bike not found", e.getMessage());
        }
    }

    @Test
    void shallCreateBike(){
        RequestBikeDTO requestBikeDTO = RequestBikeDTO.builder()
                                .brand("Kawasaki")
                                .model("Ninja 400")
                                .manufacturingYear(2023)
                                .hasElectricStart(true)
                                .build();

        ResponseBikeDTO response = bikeService.createBike(requestBikeDTO);

        assertEquals(response.getType(), "Bike");
        assertTrue(response.isHasElectricStart());
        verify(bikeRepository, times(1)).save(any());
    }

    @Test
    void shallUpdateBike(){
        Bike bike = Bike.builder().id((long)1).build();
        RequestBikeDTO requestBikeDTO = RequestBikeDTO.builder()
                                .brand("Kawasaki")
                                .model("Ninja 400")
                                .manufacturingYear(2023)
                                .hasElectricStart(true)
                                .build();

        when(bikeRepository.findById((long)1)).thenReturn(Optional.of(bike));

        ResponseBikeDTO response = bikeService.updateBike((long)1, requestBikeDTO);

        assertEquals(response.getType(), "Bike");
        assertTrue(response.isHasElectricStart());
        verify(bikeRepository, times(1)).findById((long)1);
        verify(bikeRepository, times(1)).save(any());
    }

    @Test
    void shallThrowNotFoundWhenUpdatesBike(){
        when(bikeRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            bikeService.updateBike((long)1, RequestBikeDTO.builder().build());
            fail("Exception not thrown");
        } catch (RuntimeException e){
            assertEquals("Bike not found", e.getMessage());
        }
    }

    @Test
    void shallDeleteBike(){
        Bike bike = Bike.builder().id((long)1).build();

        when(bikeRepository.findById((long)1)).thenReturn(Optional.of(bike));

        String response = bikeService.deleteBike((long)1);

        assertEquals("Bike deleted", response);
        verify(bikeRepository, times(1)).findById((long)1);
        verify(bikeRepository, times(1)).deleteById((long)1);
    }

    @Test
    void shallThrowNotFoundWhenDeletesBike(){
        when(bikeRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            bikeService.deleteBike((long)1);
            fail("Exception not thrown");
        } catch (RuntimeException e){
            assertEquals("Bike not found", e.getMessage());
        }
    }
}
