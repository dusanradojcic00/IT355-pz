package com.met.it355pz.payload;

import com.met.it355pz.payload.dto.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private Boolean success;

    private String message;

    private ProfileDTO user;

}
