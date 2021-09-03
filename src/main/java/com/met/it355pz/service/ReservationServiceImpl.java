package com.met.it355pz.service;

import com.met.it355pz.mapper.ReservationMapper;
import com.met.it355pz.model.Reservation;
import com.met.it355pz.model.User;
import com.met.it355pz.payload.dto.ReservationDTO;
import com.met.it355pz.repo.ReservationRepo;
import com.met.it355pz.repo.UserRepo;
import com.met.it355pz.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    UserRepo userRepo;

    private final ReservationMapper reservationMapper;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public List<Reservation> getAllReservationsByUser(User user) {
        return reservationRepo.getReservationsByUser(user);
    }

    @Override
    public ReservationDTO getReservationById(long id) {
        return reservationMapper.toReservationDto(reservationRepo.getById(id));
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
