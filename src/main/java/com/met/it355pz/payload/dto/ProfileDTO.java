package com.met.it355pz.payload.dto;

import com.met.it355pz.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private Address address;
}
