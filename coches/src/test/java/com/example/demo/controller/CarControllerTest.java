package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;

import static com.example.demo.Utils.asJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService service;
    
    @Test
    public void given_controller_when_getCar_By_ID_1_then_Ok() throws Exception {

        //Given
    	Car expectedCar= getCar();
        
        when(service.getCarById(1)).thenReturn(expectedCar);

        //When
        //Then
        this.mockMvc
                .perform(get("/api/cars/1")
                        .contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(asJsonString(expectedCar))));
    }

    private Car getCar() {
    	Car car = new Car(1, "volkswagen","polo", 10000);
    	return car;
    }
}
