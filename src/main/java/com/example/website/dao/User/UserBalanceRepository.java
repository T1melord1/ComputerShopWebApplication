package com.example.website.dao.User;

import com.example.website.entity.User.UserBalance;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface UserBalanceRepository {
    Optional<UserBalance> findByUserId(UUID userId);

    void createBalance(UUID userId);

    void updateBalance(BigDecimal userBalance, UUID userId);

    Optional<UserBalance> findUserBalanceByUsername(String username);
}
