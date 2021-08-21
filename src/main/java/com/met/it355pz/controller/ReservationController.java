package com.met.it355pz.controller;

import com.met.it355pz.model.Reservation;
import com.met.it355pz.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        List<Reservation> reservationList = reservationService.getAllReservations();

        return ResponseEntity.ok(reservationList);
    }

    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        long id = reservationService.saveReservation(reservation);
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return ResponseEntity.ok(map);
    }

}
