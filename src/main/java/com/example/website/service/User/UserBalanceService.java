package com.example.website.service.User;

import com.example.website.entity.User.UserBalance;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface UserBalanceService  {

    Optional<UserBalance> findUserByUserID(UUID id);

    void createBalance(UUID id);

    void updateBalance(BigDecimal userBalance, UUID id);

    Optional<UserBalance> findUserBalanceByUsername(String username);
}
