package com.met.it355pz.service;

import com.met.it355pz.model.User;
import com.met.it355pz.model.UserPrincipal;

public interface UserService {
    public boolean giveAdmin(String username);

    public boolean takeAdmin(String username);

    public long saveUser(User user);

    public User getUserById(long id, UserPrincipal currentUser);

}
