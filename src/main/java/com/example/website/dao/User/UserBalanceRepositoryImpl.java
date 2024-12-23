package com.example.website.dao.User;

import com.example.website.entity.User.User;
import com.example.website.entity.User.UserBalance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
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

    @Override
    @Transactional
    public void updateBalance(BigDecimal newBalance, UUID userId) {
        Optional<UserBalance> balanceOptional = findByUserId(userId);
        if (balanceOptional.isPresent()) {
            UserBalance userBalance = balanceOptional.get();
            userBalance.setBalance(newBalance);
            entityManager.merge(userBalance); // Обновление существующего объекта
        } else {
            // Обработка случая, когда пользователь не найден, например, выбросить исключение или создать новую запись
            throw new RuntimeException("Баланс пользователя не найден");
        }
    }

    @Override
    public Optional<UserBalance> findUserBalanceByUsername(String username) {
        try {
            // Находим пользователя по имени пользователя
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            try {
                // Находим баланс пользователя по идентификатору пользователя
                UserBalance userBalance = entityManager.createQuery("SELECT u FROM UserBalance u WHERE u.userId = :userId", UserBalance.class)
                        .setParameter("userId", user.getId())
                        .getSingleResult();
                // Логирование найденного баланса
                log.debug("Найден баланс для пользователя {}: {}", username, userBalance.getBalance());
                return Optional.of(userBalance);
            } catch (NoResultException e) {
                // Обработка случая, когда баланс пользователя не найден
                log.error("Баланс пользователя не найден для пользователя: {}", username, e);
                return Optional.empty();
            }
        } catch (NoResultException e) {
            // Обработка случая, когда пользователь не найден
            log.error("Пользователь не найден: {}", username, e);
            throw new RuntimeException("Пользователь не найден", e);
        }
    }

    @Override
    public List<UserBalance> findAllBalances() {
        return entityManager.createQuery("FROM UserBalance ", UserBalance.class).getResultList();
    }
}
