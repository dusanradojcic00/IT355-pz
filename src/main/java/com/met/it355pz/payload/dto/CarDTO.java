package com.met.it355pz.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {
    private Long id;
    private String model;
    private String maker;
    private String image;
    private int hp;
    private int gears;
    private int seats;
    private String fuel;
    private String transmission;
    private Double price;
}
