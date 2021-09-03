package com.met.it355pz.mapper;

import com.met.it355pz.model.Reservation;
import com.met.it355pz.payload.dto.ReservationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {CarMapper.class,
                UserMapper.class})
public interface ReservationMapper {
    ReservationDTO toReservationDto(Reservation reservation);
}
