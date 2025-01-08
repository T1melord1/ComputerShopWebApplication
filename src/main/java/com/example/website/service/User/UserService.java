package com.example.website.service.User;

import com.example.website.entity.User.Orders;
import com.example.website.entity.User.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void registerUser(User user);
    void saveOrders(Orders orders);

    List<User> findAllUsers();
    List<Orders> findAllOrders(@Param("userId") UUID userId);

    Optional<User> findUserByUsername(String username);

    Optional<User> findByConfirmationToken(String token);
    Optional<User> findByResetToken(String token);
    Optional<User> findByEmail(String email);
    void updateUser(User user);
}
