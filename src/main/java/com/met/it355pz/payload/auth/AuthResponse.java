package com.met.it355pz.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class AuthResponse {
    @NotEmpty
    private String username;
    @NotEmpty
    private String bearer;
}
