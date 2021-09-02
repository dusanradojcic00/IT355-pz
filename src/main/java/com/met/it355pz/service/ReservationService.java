package com.met.it355pz.service;

import com.met.it355pz.model.Reservation;
import com.met.it355pz.model.User;

import java.util.List;

public interface ReservationService {
    public List<Reservation> getAllReservations();

    public List<Reservation> getAllReservationsByUser(User user);

    public Reservation getReservationById(long id);

    public long saveReservation(Reservation reservation);

    public void deleteReservation(long id);

    public Reservation updateReservation(Reservation reservation);
}
