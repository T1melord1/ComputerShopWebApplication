package com.example.website.dao.User;

import com.example.website.entity.User.User;

public interface UserRepository {
    User add(User user);

    User findByUsername(String username);
}
