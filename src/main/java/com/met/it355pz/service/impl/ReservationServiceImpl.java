package com.met.it355pz.service.impl;

import com.met.it355pz.model.Reservation;
import com.met.it355pz.repo.ReservationRepo;
import com.met.it355pz.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepo repo;

    @Autowired
    public ReservationServiceImpl(ReservationRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return repo.findAll();
    }

    @Override
    public Reservation getReservationById(long id) {
        return null;
    }

    @Override
    public long saveReservation(Reservation reservation) {
        return repo.save(reservation).getId();
    }


}
