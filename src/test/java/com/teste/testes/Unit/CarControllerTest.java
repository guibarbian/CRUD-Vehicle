package com.teste.testes.Unit;

import com.teste.testes.DTO.car.RequestCarDTO;
import com.teste.testes.DTO.car.ResponseCarDTO;
import com.teste.testes.controller.Impl.CarControllerImpl;
import com.teste.testes.model.Car;
import com.teste.testes.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarControllerImpl carController;

    @Test
    void shallReturnCarById(){
        Car car = Car.builder().id((long)1).build();

        when(carService.getCar((long)1)).thenReturn(car);

        ResponseEntity<Car> response = carController.getCar((long)1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
        verify(carService, times(1)).getCar((long)1);
    }

    @Test
    void shallCreateCar(){
        RequestCarDTO requestCarDTO = RequestCarDTO.builder().doors(4).build();
        ResponseCarDTO responseCarDTO = ResponseCarDTO.builder().doors(4).build();

        when(carService.createCar(requestCarDTO)).thenReturn(responseCarDTO);

        ResponseEntity<ResponseCarDTO> response = carController.createCar(requestCarDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseCarDTO, response.getBody());
        verify(carService, times(1)).createCar(requestCarDTO);
    }

    @Test
    void shallUpdateCar(){
        RequestCarDTO requestCarDTO = RequestCarDTO.builder().doors(2).build();
        ResponseCarDTO responseCarDTO = ResponseCarDTO.builder().doors(2).build();

        when(carService.updateCar((long)1, requestCarDTO)).thenReturn(responseCarDTO);

        ResponseEntity<ResponseCarDTO> response = carController.updateCar((long)1, requestCarDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseCarDTO, response.getBody());
        verify(carService, times(1)).updateCar((long)1, requestCarDTO);
    }

    @Test
    void shallDeleteCar(){
        when(carService.deleteCar((long)1)).thenReturn("Car deleted");

        ResponseEntity<String> response = carController.deleteCar((long)1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Car deleted", response.getBody());
        verify(carService, times(1)).deleteCar((long)1);
    }

}
