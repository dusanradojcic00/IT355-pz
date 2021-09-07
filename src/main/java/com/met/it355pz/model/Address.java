package com.met.it355pz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String street;

    private String apartment;

    private String city;

    private String zipcode;

    @JsonIgnore
    public boolean isValid() {
        if (city == null || city.isEmpty())
            return false;
        if (street == null || street.isEmpty())
            return false;
        if (apartment == null || apartment.isEmpty())
            return false;
        if (zipcode == null || zipcode.isEmpty())
            return false;
        return true;
    }

}
