package com.met.it355pz.service;

import com.met.it355pz.exception.NoPermissionsException;
import com.met.it355pz.mapper.InvoiceMapper;
import com.met.it355pz.model.*;
import com.met.it355pz.payload.dto.InvoiceDTO;
import com.met.it355pz.repo.InvoiceRepo;
import com.met.it355pz.repo.UserRepo;

import com.met.it355pz.util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ReservationService reservationService;

    private final InvoiceMapper invoiceMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Override
    public List<InvoiceDTO> getAllInvoices(int page, int size, UserPrincipal currentUser) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "issued");

        Page<Invoice> invoices = invoiceRepo.findAll(pageable);

        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();

        invoices.forEach(invoice -> {
            invoiceDTOList.add(invoiceMapper.toInvoiceDto(invoice));
        });
        return invoiceDTOList;

    }

    @Override
    public List<InvoiceDTO> getInvoicesByUser(long userId, UserPrincipal currentUser) {

        User user = userRepo.getById(userId);

        if (!userHasPermission(user.getId(), currentUser)) {
            throw new NoPermissionsException("You don't have permission for this entity");
        }

        List<Reservation> reservations = reservationService.getAllReservationsByUser(user);
        List<InvoiceDTO> invoiceList = new ArrayList<>();

        reservations.forEach(reservation -> {
            System.out.println("Reservation: " + reservation.getId());
            Invoice invoice = invoiceRepo.findInvoiceByReservation(reservation);
            invoiceList.add(invoiceMapper.toInvoiceDto(invoice));
        });

        return invoiceList;
    }

    /**
     * @param invoiceId
     * @param currentUser
     * @return InvoiceDTO
     * @throws NoPermissionsException if user is not an Admin and requests invoice which he is not owner of
     */
    @Override
    public InvoiceDTO getInvoiceById(long invoiceId, UserPrincipal currentUser) {
        Invoice invoice = invoiceRepo.getById(invoiceId);
        if (userHasPermission(invoice.getReservation().getUser().getId(), currentUser)) {
            return invoiceMapper.toInvoiceDto(invoice);
        } else {
            throw new NoPermissionsException("You don't have permission");
        }
    }

    @Override
    public long createInvoice(Invoice invoice) {
        return invoiceRepo.save(invoice).getId();
    }

    @Override
    public void deleteInvoice(Invoice invoice) {
        invoiceRepo.delete(invoice);
    }

    private boolean userHasPermission(long userId, UserPrincipal currentUser) {
        //If user is ADMIN return true
        if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.name())))
            return true;
        //If userId from resource is equal to loggedUser ID
        if (userId == currentUser.getId())
            return true;
        return false;
    }
}
