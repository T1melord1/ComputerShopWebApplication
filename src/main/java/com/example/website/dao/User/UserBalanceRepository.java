package com.example.website.dao.User;

import com.example.website.entity.User.UserBalance;

import java.util.Optional;
import java.util.UUID;

public interface UserBalanceRepository {
    Optional<UserBalance> findByUserId(UUID userId);

    void createBalance(UUID userId);
}
