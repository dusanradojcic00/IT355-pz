package com.met.it355pz.controller;

import com.met.it355pz.config.CurrentUser;
import com.met.it355pz.mapper.InvoiceMapper;
import com.met.it355pz.model.Invoice;
import com.met.it355pz.model.UserPrincipal;
import com.met.it355pz.payload.dto.InvoiceDTO;
import com.met.it355pz.service.InvoiceService;
import com.met.it355pz.util.AppConstants;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")

public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> getAllInvoices(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                            @RequestParam(name = "user", required = false) Long userId,
                                            @CurrentUser UserPrincipal currentUser) {
        if (userId == null) {
            return ResponseEntity.ok(invoiceService.getAllInvoices(page, size, currentUser));
        } else {
            return ResponseEntity.ok(invoiceService.getInvoicesByUser(userId, currentUser));
        }

    }


    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.createInvoice(invoice));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteInvoice(@RequestBody Invoice invoice) {
        invoiceService.deleteInvoice(invoice);
        return ResponseEntity.ok("Succesfully deleted invoice with ID: " + invoice.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getinvoiceById(@PathVariable Long id,
                                            @CurrentUser UserPrincipal currentUser) {

        return ResponseEntity.ok(invoiceService.getInvoiceById(id, currentUser));
    }
}
