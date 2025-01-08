package com.example.website.dao.User;

import com.example.website.entity.User.Orders;
import com.example.website.entity.User.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findByConfirmationToken(String token);
    Optional<User> findByResetToken(String token);
    Optional<User> findByEmail(String email);
    List<Orders> findAllOrders(UUID userId);
    void saveOrders(Orders orders);
}
