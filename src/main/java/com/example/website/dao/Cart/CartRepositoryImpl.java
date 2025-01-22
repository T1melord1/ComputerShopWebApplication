package com.example.website.dao.Cart;

import com.example.website.entity.User.Orders;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CartRepositoryImpl implements CartRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Orders> findAllOrders(UUID userId) {
        return entityManager.createQuery("SELECT o FROM Orders o WHERE o.user.id = :userId", Orders.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void saveOrders(Orders orders) {
        entityManager.merge(orders);
    }
}
