package com.example.website.dao.User;

import com.example.website.entity.User.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findByConfirmationToken(String token);
    Optional<User> findByEmail(String email);
}
