package com.met.it355pz.repo;

import com.met.it355pz.model.Invoice;
import com.met.it355pz.model.Reservation;
import com.met.it355pz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    public Invoice findInvoiceByReservation(Reservation reservation);
}
