package com.met.it355pz.controller;

import com.met.it355pz.model.Role;
import com.met.it355pz.model.RoleType;
import com.met.it355pz.model.User;
import com.met.it355pz.payload.ApiResponse;
import com.met.it355pz.payload.auth.AuthRequest;
import com.met.it355pz.payload.auth.AuthResponse;
import com.met.it355pz.repo.RoleRepo;
import com.met.it355pz.repo.UserRepo;
import com.met.it355pz.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = tokenUtil.generateToken(authRequest.getUsername());

        return ResponseEntity.ok(new AuthResponse(authRequest.getUsername(), jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {

        User user = new User(authRequest.getUsername(), passwordEncoder.encode(authRequest.getPassword()));

        List<Role> roles = new ArrayList<>();

        roles.add(roleRepo.findByName(RoleType.ROLE_USER));

        //Prvi user je i admin
        if (userRepo.count() < 1){
            roles.add(roleRepo.findByName(RoleType.ROLE_ADMIN));
        }

        user.setRoles(roles);

        User savedUser = userRepo.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "Uspesno ste kreirali nalog!"));
    }


}
