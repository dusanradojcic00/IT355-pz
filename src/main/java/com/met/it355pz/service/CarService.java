package com.met.it355pz.service;

import com.met.it355pz.model.Car;

import java.util.List;

public interface CarService {
    public List<Car> getAllCars();

    public Car getCarById(long id);

    public void deleteCar(long id);

    public long saveCar(Car car);


    public Car updateCar(Car car);

    public List<Car> availableCars(String startDate, String endDate);
}
