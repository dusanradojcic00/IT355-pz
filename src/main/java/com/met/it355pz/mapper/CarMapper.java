package com.met.it355pz.mapper;

import com.met.it355pz.model.Car;
import com.met.it355pz.payload.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    public CarDTO toCarDto(Car car);
}
