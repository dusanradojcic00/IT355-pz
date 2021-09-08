package com.met.it355pz.service;

import com.met.it355pz.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Test
    void saveUser() {
        User user = new User("marko@gmail.com", "marko123");

    }
}