package com.met.it355pz.controller;

import com.met.it355pz.model.Car;
import com.met.it355pz.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        long id = carService.saveCar(car);
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("deleted");
    }

    @PutMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> updateCar(@RequestBody Car car) {
        return null;
    }

}
