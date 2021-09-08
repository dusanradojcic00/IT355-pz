package com.met.it355pz.controller;

import com.met.it355pz.config.AuthFilter;
import com.met.it355pz.model.Car;
import com.met.it355pz.service.CarService;
import com.met.it355pz.service.CustomUserDetailsServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest(controllers = CarController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControllerTest {

    @MockBean
    private CarService carService;

    @MockBean
    private AuthFilter authFilter;

    @MockBean
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/cars Listing all cars from database")
    void getAll() throws Exception {


        Car bmw = new Car(123L, "NI123PM", "520d", "BMW", "https://www.google.com", 200, 6, 5, "Gasoline", "Auto", 50.0);
        Car audi = new Car(234L, "NI234AB", "A6", "Audi", "https://www.google.com", 200, 6, 5, "Diesel", "Manual", 40.0);
        Car mercedes = new Car(345L, "NI345CD", "G Wagon", "Mercedes", "https://www.google.com", 300, 8, 7, "Gasoline", "Auto", 80.0);

        Mockito.when(carService.getAllCars()).thenReturn(Arrays.asList(bmw, audi, mercedes));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].plates", Matchers.is("NI123PM")));
    }
}