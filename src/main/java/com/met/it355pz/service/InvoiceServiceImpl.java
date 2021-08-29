package com.met.it355pz.service;

import com.met.it355pz.model.Invoice;
import com.met.it355pz.repo.InvoiceRepo;
import com.met.it355pz.service.InvoiceService;
import com.met.it355pz.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collections;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepo invoiceRepo;

    @Override
    public List<Invoice> getAllInvoices(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "issued");

        Page<Invoice> invoices = invoiceRepo.findAll(pageable);

        return invoices.toList();

    }

    @Override
    public List<Invoice> getInvoicesByUser(long userId) {
        throw new NotImplementedException();
    }

    @Override
    public Invoice getInvoiceById(long invoiceId) {
        throw new NotImplementedException();
    }

    @Override
    public long createInvoice(Invoice invoice) {
        return invoiceRepo.save(invoice).getId();
    }
}
