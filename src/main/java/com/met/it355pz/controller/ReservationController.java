package com.met.it355pz.controller;

import com.met.it355pz.model.Reservation;
import com.met.it355pz.service.ReservationService;
import com.met.it355pz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllReservations() {
        List<Reservation> reservationList = reservationService.getAllReservations();
        return ResponseEntity.ok(reservationList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        long id = reservationService.saveReservation(reservation);
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return ResponseEntity.ok(map);
    }

}
