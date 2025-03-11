package com.example.demo.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Bike;
import com.example.demo.repository.BikeRepository;
import com.example.demo.service.impl.BikeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BikeServiceTest {
    
    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private BikeServiceImpl bikeService;

    @Test
    void testGetBike(){
        Bike bike = Bike.builder().id((long)1).hasElectricStart(false).build();

        when(bikeRepository.findById((long)1)).thenReturn(Optional.of(bike));

        Bike response = bikeService.getBike((long)1);

        assertEquals(bike, response);
        verify(bikeRepository.findById((long)1));
    }
}
