package com.example.website.service.User;

import com.example.website.entity.User.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);

    User findUserByUsername(String username);


    List<User> findAllUsers();
}
