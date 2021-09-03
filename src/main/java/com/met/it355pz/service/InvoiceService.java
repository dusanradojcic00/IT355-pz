package com.met.it355pz.service;

import com.met.it355pz.model.Invoice;
import com.met.it355pz.model.UserPrincipal;
import com.met.it355pz.payload.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    public List<InvoiceDTO> getAllInvoices(int page, int size, UserPrincipal currentUser);

    public List<InvoiceDTO> getInvoicesByUser(long userId, UserPrincipal currentUser);

    public InvoiceDTO getInvoiceById(long invoiceId, UserPrincipal currentUser);

    public long createInvoice(Invoice invoice);

    public void deleteInvoice(Invoice invoice);
}
