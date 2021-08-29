package com.met.it355pz.service;

import com.met.it355pz.model.Reservation;
import com.met.it355pz.model.User;
import com.met.it355pz.repo.ReservationRepo;
import com.met.it355pz.repo.UserRepo;
import com.met.it355pz.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepo reservationRepo;

    UserRepo userRepo;

    @Autowired
    public ReservationServiceImpl(ReservationRepo reservationRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.reservationRepo = reservationRepo;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public Reservation getReservationById(long id) {
        return reservationRepo.getById(id);
    }

    @Override
    public long saveReservation(Reservation reservation) {
        User user = userRepo.getById(reservation.getUser().getId());
        reservation = reservationRepo.save(reservation);
        user.addReservation(reservation);
        userRepo.save(user);
        return reservation.getId();
    }

    @Override
    public void deleteReservation(long id) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
