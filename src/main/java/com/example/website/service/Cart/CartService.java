package com.example.website.service.Cart;

import com.example.website.entity.Videocard.Videocard;

import java.util.List;

public interface CartService {
    List<Videocard> getVideocardsInCart();

    String addToCart(Integer id);

    void deleteVideocardFromCart(Integer id);

    void clearCart();
}
