package com.example.website.service.Cart;

import com.example.website.entity.Videocard;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CartService {
    List<Videocard> getVideocardsInCart();

    void addToCart(Integer id);

    void deleteVideocardFromCart(Integer id);
}
