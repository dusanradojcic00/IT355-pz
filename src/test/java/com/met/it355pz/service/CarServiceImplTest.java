package com.met.it355pz.service;

import com.met.it355pz.model.Car;
import com.met.it355pz.model.Reservation;
import com.met.it355pz.repo.CarRepo;
import com.met.it355pz.repo.ReservationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepo carRepo;

    @Mock
    private ReservationRepo reservationRepo;

    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarServiceImpl(carRepo, reservationRepo);
    }

    @Test
    void getCarById() {
        Car car = new Car(123L, "NI123PM", "520d", "BMW", "https://www.google.com", 200, 6, 5, "Gasoline", "Auto", 50.0);
        //CarDTO carExpectedResponse = new CarDTO(123L, "520d", "BMW", "https://www.google.com", 200, 6, 5, "Gasoline", "Auto", 50.0);
        Mockito.when(carRepo.findById(123L)).thenReturn(Optional.of(car));
        Car response = carService.getCarById(123L);
        assertEquals(car, response);
    }

    @Test
    void getAllCars() {
        Car bmw = new Car(123L, "NI123PM", "520d", "BMW", "https://www.google.com", 200, 6, 5, "Gasoline", "Auto", 50.0);
        Car audi = new Car(234L, "NI123PM", "A6", "Audi", "https://www.google.com", 200, 6, 5, "Gasoline", "Auto", 50.0);
        List<Car> carList = new ArrayList<>();
        carList.add(bmw);
        carList.add(audi);
        Mockito.when(carRepo.findAll()).thenReturn(carList);

        List<Car> responseList = carService.getAllCars();
        assertEquals(responseList, carList);
    }

    @Test
    void getAvailable() {
        String startDate = "2021-09-08";
        String endDate = "2021-09-10";

        Car bmw = new Car(123L, "NI123PM", "520d", "BMW", "https://www.google.com", 200, 6, 5, "Gasoline", "Auto", 50.0);
        Car audi = new Car(234L, "NI234AB", "A6", "Audi", "https://www.google.com", 200, 6, 5, "Diesel", "Manual", 40.0);
        Car mercedes = new Car(345L, "NI345CD", "G Wagon", "Mercedes", "https://www.google.com", 300, 8, 7, "Gasoline", "Auto", 80.0);

        List<Car> carList = new ArrayList<>();
        carList.add(bmw);
        carList.add(audi);
        carList.add(mercedes);

        Mockito.when(carRepo.findAll()).thenReturn(carList);

        Reservation reservation1 = new Reservation(123L, LocalDate.of(2021, 9, 8), LocalDate.of(2021, 9, 9), bmw);
        Reservation reservation2 = new Reservation(123L, LocalDate.of(2021, 9, 5), LocalDate.of(2021, 9, 7), audi);
        Reservation reservation3 = new Reservation(123L, LocalDate.of(2021, 9, 11), LocalDate.of(2021, 9, 15), mercedes);

        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation1);
        reservationList.add(reservation2);
        reservationList.add(reservation3);

        Mockito.when(reservationRepo.findAllByCar(bmw)).thenReturn(new ArrayList<>(Arrays.asList(reservation1)));
        Mockito.when(reservationRepo.findAllByCar(audi)).thenReturn(new ArrayList<>(Arrays.asList(reservation2)));
        Mockito.when(reservationRepo.findAllByCar(mercedes)).thenReturn(new ArrayList<>(Arrays.asList(reservation3)));


        List<Car> availableCars = carService.availableCars(startDate, endDate);
        List<Car> expectedList = new ArrayList<>();
        expectedList.add(audi);
        expectedList.add(mercedes);

        assertEquals(expectedList, availableCars);

    }
}