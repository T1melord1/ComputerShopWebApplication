package com.example.website.controller;

import com.example.website.entity.Videocard;
import com.example.website.service.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getVideocardsInCart());
        return "cartJSP/cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Integer id) {
        cartService.addToCart(id);
        return "redirect:/videocards";
    }

    @PostMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable Integer id) {
        cartService.deleteVideocardFromCart(id);
        return "redirect:/cart";
    }
}
