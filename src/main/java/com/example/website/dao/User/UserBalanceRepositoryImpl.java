package com.example.website.dao.User;

import com.example.website.entity.User.UserBalance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserBalanceRepositoryImpl implements UserBalanceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserBalance> findByUserId(UUID userId) {
        try {
            UserBalance userBalance = entityManager.createQuery("SELECT u FROM UserBalance u WHERE u.userId = :userId", UserBalance.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return Optional.ofNullable(userBalance);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void createBalance(UUID userId) {
        UserBalance userBalance = new UserBalance();
        userBalance.setUserId(userId);
        userBalance.setCurrency("BYN");
        userBalance.setBalance(BigDecimal.ZERO);
        entityManager.persist(userBalance);
    }

}
