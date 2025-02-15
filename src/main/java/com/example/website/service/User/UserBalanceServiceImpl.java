package com.example.website.service.User;

import com.example.website.dao.User.UserBalanceRepository;
import com.example.website.entity.User.UserBalance;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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
    public List<UserBalance> findAllBalances() {
        log.debug("Поиск всех балансов пользователей");
        return userBalanceRepository.findAllBalances();
    }


    @Override
    @Transactional
    public void createBalance(UUID id) {
        log.debug("Создание баланса пользователя: {}", id);
        userBalanceRepository.createBalance(id);
    }

    @Override
    @Transactional
    public void updateBalance(BigDecimal newBalance, UUID id) {
    log.debug("Обновление баланса польз: {}", newBalance);
    userBalanceRepository.updateBalance(newBalance, id);
    }

    @Override
    public Optional<UserBalance> findUserBalanceByUsername(String username){
        log.debug("Поиск баланса пользователя по ник-нейму: {}", username);
        return userBalanceRepository.findUserBalanceByUsername(username);
    }
}
