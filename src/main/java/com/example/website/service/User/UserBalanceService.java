package com.example.website.service.User;

import com.example.website.entity.User.User;
import com.example.website.entity.User.UserBalance;

import java.util.Optional;
import java.util.UUID;

public interface UserBalanceService {

    Optional<UserBalance> findUserByUserID(UUID id);

    void createBalance(UUID id);
}
