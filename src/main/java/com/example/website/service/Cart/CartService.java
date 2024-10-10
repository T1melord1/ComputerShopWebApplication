package com.example.website.service.Cart;

import com.example.website.entity.Videocard;

import java.util.List;

public interface CartService {
    List<Videocard> getVideocardsInCart();

    void addToCart(Integer id);

    void deleteVideocardFromCart(Integer id);
}
