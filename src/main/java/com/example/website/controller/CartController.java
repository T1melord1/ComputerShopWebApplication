package com.example.website.controller;

import com.example.website.service.Cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getVideocardsInCart());
        return "cartJSP/cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Integer id) {
        cartService.addToCart(id);
        return "redirect:/videocards";
    }

    @PostMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id") Integer id) {
        cartService.deleteVideocardFromCart(id);
        return "redirect:/cart";
    }
}
