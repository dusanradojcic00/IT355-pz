package com.met.it355pz.service.impl;

import com.met.it355pz.model.Role;
import com.met.it355pz.model.RoleType;
import com.met.it355pz.model.User;
import com.met.it355pz.model.UserPrincipal;
import com.met.it355pz.repo.RoleRepo;
import com.met.it355pz.repo.UserRepo;
import com.met.it355pz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public boolean giveAdmin(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));

        user.addRole(roleRepo.findByName(RoleType.ROLE_ADMIN));

        userRepo.save(user);

        return true;
    }

    @Override
    public boolean takeAdmin(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));

        Role adminRole = roleRepo.findByName(RoleType.ROLE_ADMIN);

        if (user.getRoles().contains(adminRole)) {
            user.removeRole(adminRole);
            userRepo.save(user);
        }

        return true;

    }

    @Override
    public long saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user).getId();
    }

    @Override
    public User getUserById(long id, UserPrincipal currentUser) {
        User user = userRepo.findByUsername(currentUser.getUsername()).orElse(new User());

        if (user.getId().equals(currentUser.getId()) ||
                currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.name()))) {
            return userRepo.getById(id);
        }
        throw new EntityNotFoundException("Entitiy with ID: " + id + " not found");
    }
}
