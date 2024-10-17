package com.example.website.service.User;

import com.example.website.entity.User.User;

public interface UserService {
        void registerUser(User user);
        User findUserByUsername(String username);

        User authenticate(String username, String password);
}
