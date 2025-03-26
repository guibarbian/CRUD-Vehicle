package com.teste.testes.Integration;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.testes.DTO.RequestVehicleDTO;
import com.teste.testes.model.Bike;
import com.teste.testes.model.Car;
import com.teste.testes.model.Truck;
import com.teste.testes.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setup(){
        vehicleRepository.deleteAll();
    }

    @Test
    @DisplayName("Shall return a list of all the vehicles")
    public void getVehiclesTest() throws Exception{
        Car car = Car.builder()
                .model("car").build();
        Bike bike = Bike.builder()
                .model("bike").build();
        Truck truck = Truck.builder()
                .model("truck").build();

        vehicleRepository.save(car);
        vehicleRepository.save(bike);
        vehicleRepository.save(truck);

        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[2].model").value("truck"));
    }

    @Test
    @DisplayName("Shall return a specific vehicle")
    public void shallReturnSpecificVehicle() throws Exception{
        Car car = vehicleRepository.save(Car.builder().model("car").build());

        mockMvc.perform(get("/vehicles/" + car.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("car"));
    }

    @Test
    @DisplayName("Shall throw NotFound when searching for a vehicle")
    public void shallReturnNotFoundWhenSearchingForVehicle() throws Exception{
        mockMvc.perform(get("/vehicles/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Vehicle not found"));
    }

    @Test
    @DisplayName("Shall create a new Vehicle")
    public void shallPostNewVehicle() throws Exception{
        RequestVehicleDTO request = RequestVehicleDTO.builder()
                .type("car").doors(4).build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("car"))
                .andExpect(jsonPath("$.doors").value(4));
    }

    @Test
    @DisplayName("Shall throw BadRequest when tries to create a vehicle with invalid type")
    public void shallThrowBadRequestWhenCreatesVehicle() throws Exception{
        RequestVehicleDTO request = RequestVehicleDTO.builder()
                .type("invalid").doors(4).build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Invalid vehicle type"));
    }

    @Test
    @DisplayName("Shall throw BadRequest when tries to create vehicle with invalid combination")
    public void shallThrowBadRequestWhenCreatesInvalidVehicle() throws Exception{
        RequestVehicleDTO request = RequestVehicleDTO.builder()
                .type("car").maxCargo(1000).build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Invalid vehicle type"));
    }

    @Test
    @DisplayName("Shall Return Updated Vehicle")
    public void shallUpdateVehicle() throws Exception{
        Car car = vehicleRepository.save(Car.builder().type("car").doors(4).build());

        RequestVehicleDTO request = RequestVehicleDTO.builder()
                .type("car").doors(2).build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(put("/vehicles/" + car.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("car"))
                .andExpect(jsonPath("$.doors").value(2));
    }

    @Test
    @DisplayName("Throws NotFound when tries to update a vehicle that doesn't exists")
    public void shallThrowNotFoundWhenUpdatesInvalidId() throws Exception{
        RequestVehicleDTO request = RequestVehicleDTO.builder()
                .type("car").doors(2).build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(put("/vehicles/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Vehicle not found"));
    }

    @Test
    @DisplayName("Shall throw BadRequest when tries to update the type of the Vehicle")
    public void shallThrowBadRequestWhenTriesToUpdateType() throws Exception{
        Car car = vehicleRepository.save(Car.builder().type("car").doors(4).build());

        RequestVehicleDTO request = RequestVehicleDTO.builder()
                .type("truck").doors(2).build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(put("/vehicles/" + car.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Vehicle type cannot be changed"));
    }

    @Test
    @DisplayName("Shall throw Bad Request when tries to update a Vehicle with a invalid combination")
    public void shallThrowBadRequestWhenTriesToUpdateVehicleWithInvalidCombination() throws Exception{
        Car car = vehicleRepository.save(Car.builder().type("car").doors(4).build());

        RequestVehicleDTO request = RequestVehicleDTO.builder()
                .type("car").maxCargo(1000).build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(put("/vehicles/" + car.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Invalid vehicle type"));
    }

    @Test
    @DisplayName("Shall delete an existing vehicle")
    public void shallDeleteVehicle() throws Exception{
        Car car = vehicleRepository.save(Car.builder().type("car").doors(4).build());

        mockMvc.perform(delete("/vehicles/" + car.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Vehicle deleted successfully"));    }

    @Test
    @DisplayName("Shall throw a NotFound when tries to delete a vehicle that's not in the database")
    public void shallThrowNotFoundWhenDeletes() throws Exception{
        mockMvc.perform(delete("/vehicles/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Vehicle not found"));
    }
}
