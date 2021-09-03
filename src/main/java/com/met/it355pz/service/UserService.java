package com.met.it355pz.service;

import com.met.it355pz.model.User;
import com.met.it355pz.model.UserPrincipal;
import com.met.it355pz.payload.dto.ProfileDTO;
import com.met.it355pz.payload.dto.UserDTO;

public interface UserService {
    public boolean giveAdmin(String username);

    public boolean takeAdmin(String username);

    public long saveUser(User user);

    public ProfileDTO getUserById(long id, UserPrincipal currentUser);

    void deleteUser(User user);

    public ProfileDTO registerUser(User user);

    void updateUser(User user, UserPrincipal currentUser);

    public UserDTO getUserByUsername(String username);
}
