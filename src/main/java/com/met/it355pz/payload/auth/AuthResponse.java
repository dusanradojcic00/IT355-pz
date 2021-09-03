package com.met.it355pz.payload.auth;

import com.met.it355pz.payload.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class AuthResponse {
    @NotEmpty
    private UserDTO user;
    @NotEmpty
    private String bearer;
}
