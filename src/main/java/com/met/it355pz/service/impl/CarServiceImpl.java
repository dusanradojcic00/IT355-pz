package com.met.it355pz.service.impl;

import com.met.it355pz.exception.NoSuchFoundElementException;
import com.met.it355pz.model.Car;
import com.met.it355pz.repo.CarRepo;
import com.met.it355pz.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepo carRepo;

    @Autowired
    public CarServiceImpl(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    @Override
    public Car getCarById(long id) {
        return carRepo.findById(id).orElseThrow(() -> new NoSuchFoundElementException(id));
    }

    @Override
    public void deleteCar(long id) {
        carRepo.deleteById(id);
    }

    @Override
    public long saveCar(Car car) {
        return carRepo.save(car).getId();
    }

    @Override
    public Car updateCar(Car car) {
        return carRepo.save(car);
    }
}
