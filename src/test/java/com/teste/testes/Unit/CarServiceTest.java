package com.teste.testes.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.teste.testes.DTO.car.RequestCarDTO;
import com.teste.testes.DTO.car.ResponseCarDTO;
import com.teste.testes.model.Car;
import com.teste.testes.repository.CarRepository;
import com.teste.testes.service.IMPL.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void shallReturnCar(){
        Car car = Car.builder().id((long)1).build();

        when(carRepository.findById((long)1)).thenReturn(Optional.of(car));

        Car response = carService.getCar((long)1);

        assertEquals(response, car);
        verify(carRepository, times(1)).findById((long)1);
    }

    @Test
    void shallThrowExceptionWhenCarNotFound(){
        when(carRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            carService.getCar((long)1);
        } catch (RuntimeException e){
            assertEquals("Car not found", e.getMessage());
        }
    }

    @Test
    void shallCreateCar(){
        RequestCarDTO requestCarDTO = RequestCarDTO.builder()
                                                    .brand("Ford")
                                                    .model("Fiesta")
                                                    .manufacturingYear(2021)
                                                    .doors(4)
                                                    .build();

        ResponseCarDTO response = carService.createCar(requestCarDTO);

        assertEquals("Car", response.getType());
        assertEquals("Ford", response.getBrand());
        verify(carRepository, times(1)).save(any());
    }

    @Test
    void shallUpdateCar(){
        Car car = Car.builder().id((long)1).doors(4).build();

        RequestCarDTO dto = RequestCarDTO.builder()
                                        .doors(2)
                                        .build();

        when(carRepository.findById((long)1)).thenReturn(Optional.of(car));

        ResponseCarDTO response = carService.updateCar((long)1, dto);

        assertEquals(2, response.getDoors());
        verify(carRepository, times(1)).findById((long)1);
        verify(carRepository, times(1)).save(any());
    }

    @Test
    void shallThrowNotFoundWhenUpdatesCar(){
        when(carRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            carService.updateCar((long)1, RequestCarDTO.builder().build());
        } catch (RuntimeException e){
            assertEquals("Car not found", e.getMessage());
        }
    }

    @Test
    void shallDeleteCar(){
        Car car = Car.builder().id((long)1).build();

        when(carRepository.findById((long)1)).thenReturn(Optional.of(car));

        carService.deleteCar((long)1);

        verify(carRepository, times(1)).findById((long)1);
        verify(carRepository, times(1)).deleteById((long)1);
    }

    @Test
    void shallThrowNotFoundWhenDeletesCar(){
        when(carRepository.findById((long)1)).thenReturn(Optional.empty());

        try {
            carService.deleteCar((long)1);
        } catch (RuntimeException e){
            assertEquals(404, e.hashCode());
            assertEquals("Car not found", e.getMessage());
        }
    }
}
