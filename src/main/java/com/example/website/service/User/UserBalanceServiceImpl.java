package com.example.website.service.User;

import com.example.website.dao.User.UserBalanceRepository;
import com.example.website.entity.User.UserBalance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserBalanceServiceImpl implements UserBalanceService {
    private final UserBalanceRepository userBalanceRepository;

    @Override
    public Optional<UserBalance> findUserByUserID(UUID id) {
        log.debug("Поиск по ID пользователя: {}", id);
        return userBalanceRepository.findByUserId(id);
    }

    @Override
    public void createBalance(UUID id) {
        log.debug("Создание баланса пользователя: {}", id);
        userBalanceRepository.createBalance(id);
    }
}
