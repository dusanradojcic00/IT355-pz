package com.met.it355pz.repo;

import com.met.it355pz.model.Reservation;
import com.met.it355pz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    public List<Reservation> getReservationsByUser(User user);
}
