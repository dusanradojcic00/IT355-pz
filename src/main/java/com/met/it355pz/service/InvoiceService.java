package com.met.it355pz.service;

import com.met.it355pz.model.Invoice;
import com.met.it355pz.model.UserPrincipal;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> getAllInvoices(int page, int size, UserPrincipal currentUser);

    public List<Invoice> getInvoicesByUser(long userId,  UserPrincipal currentUser);

    public Invoice getInvoiceById(long invoiceId, UserPrincipal currentUser);

    public long createInvoice(Invoice invoice);

    public void deleteInvoice(Invoice invoice);
}
