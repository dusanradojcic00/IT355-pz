package com.met.it355pz.controller;

import com.met.it355pz.config.CurrentUser;
import com.met.it355pz.model.User;
import com.met.it355pz.model.UserPrincipal;
import com.met.it355pz.payload.auth.AuthRequest;
import com.met.it355pz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/giveadmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> giveAdmin(@RequestBody AuthRequest authRequest) {
        userService.giveAdmin(authRequest.getUsername());
        return ResponseEntity.ok("User got admin " + authRequest.getUsername());
    }

    @PostMapping("/takeadmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> takeAdmin(@RequestBody AuthRequest authRequest) {
        userService.takeAdmin(authRequest.getUsername());
        return ResponseEntity.ok("User admin revoked " + authRequest.getUsername());

        //return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Method not implemented yet");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id, @CurrentUser UserPrincipal userPrincipal) {
        User user = userService.getUserById(id, userPrincipal);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        long id = userService.saveUser(user);
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return ResponseEntity.ok("User with username " + user.getUsername() + " successfully deleted");
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> updateUser(@RequestBody User user, @CurrentUser UserPrincipal userPrincipal) {
        userService.updateUser(user, userPrincipal);
        return ResponseEntity.ok("User with username " + user.getUsername() + " successfully updated");
    }
}
