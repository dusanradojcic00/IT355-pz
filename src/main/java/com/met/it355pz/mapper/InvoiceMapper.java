package com.met.it355pz.mapper;

import com.met.it355pz.model.Invoice;
import com.met.it355pz.payload.dto.InvoiceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {ReservationMapper.class})
public interface InvoiceMapper {
    InvoiceDTO toInvoiceDto(Invoice invoice);
}
