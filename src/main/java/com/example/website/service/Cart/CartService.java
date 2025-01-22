package com.example.website.service.Cart;

import com.example.website.entity.User.Orders;
import com.example.website.entity.Videocard.Videocard;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<Videocard> getVideocardsInCart();

    String addToCart(Integer id);

    void deleteVideocardFromCart(Integer id);

    void clearCart();

    void saveOrders(Orders orders);

    List<Orders> findAllOrders(@Param("userId") UUID userId);

    void processOrders(UserDetails userDetails, RedirectAttributes redirectAttributes);

    void showOrders(UserDetails userDetails, Model model);

}
