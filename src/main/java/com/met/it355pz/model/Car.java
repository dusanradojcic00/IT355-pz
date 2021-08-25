package com.met.it355pz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String plates;
    private String model;
    private String maker;
    private String image;
    private int hp;
    private int gears;
    private int seats;
    private String fuel;
    private String transmission;
    private Double price;

    @Override
    public String toString() {
        return "Car: " + model + " " + maker;
    }
}
