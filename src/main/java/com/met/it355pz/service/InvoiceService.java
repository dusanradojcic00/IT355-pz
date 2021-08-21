package com.met.it355pz.service;

import com.met.it355pz.model.Invoice;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> getAllInvoices();
    public List<Invoice> getInvoicesByUser(long userId);
    public Invoice getInvoiceById(long invoiceId);
}
