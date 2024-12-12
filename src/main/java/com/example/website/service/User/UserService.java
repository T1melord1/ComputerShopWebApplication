package com.example.website.service.User;

import com.example.website.entity.User.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(User user);


    List<User> findAllUsers();

    Optional<User> findUserByUsername(String username);

    Optional<User> findByConfirmationToken(String token);
    Optional<User> findByResetToken(String token);
    Optional<User> findByEmail(String email);
    void updateUser(User user);
}
