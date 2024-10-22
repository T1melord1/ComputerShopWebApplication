package com.example.website.dao.User;

import com.example.website.entity.User.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUsername(String username);
}
