package com.met.it355pz.service;

import com.met.it355pz.model.Reservation;

import java.util.List;

public interface ReservationService {
    public List<Reservation> getAllReservations();

    public Reservation getReservationById(long id);

    public long saveReservation(Reservation reservation);
}
