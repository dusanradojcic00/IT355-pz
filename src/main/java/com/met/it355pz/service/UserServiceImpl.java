package com.met.it355pz.service;

import com.met.it355pz.exception.InputValidationException;
import com.met.it355pz.exception.NoPermissionsException;
import com.met.it355pz.exception.NoSuchElementFoundException;
import com.met.it355pz.mapper.UserMapper;
import com.met.it355pz.model.Role;
import com.met.it355pz.model.RoleType;
import com.met.it355pz.model.User;
import com.met.it355pz.model.UserPrincipal;
import com.met.it355pz.payload.dto.ProfileDTO;
import com.met.it355pz.payload.dto.UserDTO;
import com.met.it355pz.repo.RoleRepo;
import com.met.it355pz.repo.UserRepo;
import com.met.it355pz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    private final UserMapper userMapper;

    @Override
    public boolean giveAdmin(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NoSuchElementFoundException(username));

        user.addRole(roleRepo.findByName(RoleType.ROLE_ADMIN));

        userRepo.save(user);

        return true;
    }

    @Override
    public boolean takeAdmin(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NoSuchElementFoundException(username));

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

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findByName(RoleType.ROLE_USER));
        user.setRoles(roles);

        return userRepo.save(user).getId();
    }

    @Override
    public ProfileDTO registerUser(User user) {
        List<Role> roles = new ArrayList<>();

        roles.add(roleRepo.findByName(RoleType.ROLE_USER));

        //Prvi user je i admin
        if (userRepo.count() < 1) {
            roles.add(roleRepo.findByName(RoleType.ROLE_ADMIN));
        }

        user.setRoles(roles);

        try {
            user = userRepo.save(user);
        } catch (ConstraintViolationException ex) {
            throw new InputValidationException("Field username must be formated as email: " + user.getUsername());
        }

        return userMapper.toProfileDto(user);
    }

    @Override
    public void updateUser(User user, UserPrincipal currentUser) {
        User newUser = userRepo.findByUsername(user.getUsername()).orElseThrow(() -> new NoSuchElementFoundException(user.getUsername()));
        if (user.getFirstName() != null)
            newUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            newUser.setLastName(user.getLastName());
        if (user.getPhone() != null)
            newUser.setPhone(user.getPhone());
        if (user.getAddress() != null)
            if (user.getAddress().isValid())
                newUser.setAddress(user.getAddress());
        userRepo.save(newUser);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NoSuchElementFoundException(username));
        return userMapper.toUserDto(user);

    }

    @Override
    public ProfileDTO getUserById(long id, UserPrincipal currentUser) {
        User user = userRepo.findById(id).orElseThrow(() -> new NoSuchElementFoundException(id));

        if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.name())) || user.getId().equals(currentUser.getId())) {
            return userMapper.toProfileDto(userRepo.getById(id));
        } else {
            throw new NoPermissionsException("You don't have permission to access this entity!");
        }

    }

    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }


    @Override
    public ProfileDTO getUserProfile(UserPrincipal userPrincipal) {
        User user = userRepo.findByUsername(userPrincipal.getUsername()).orElseThrow(() -> new NoSuchElementFoundException(userPrincipal.getUsername()));
        return userMapper.toProfileDto(user);
    }

}
