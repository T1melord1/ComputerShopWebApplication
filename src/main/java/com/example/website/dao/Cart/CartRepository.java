package com.example.website.dao.Cart;

import com.example.website.entity.User.Orders;

import java.util.List;
import java.util.UUID;

public interface CartRepository {
    List<Orders> findAllOrders(UUID userId);
    void saveOrders(Orders orders);
}
