package com.example.website.controller;

import com.example.website.entity.User.User;
import com.example.website.entity.User.UserBalance;
import com.example.website.service.Cart.CartService;
import com.example.website.service.User.UserBalanceService;
import com.example.website.service.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final UserBalanceService userBalanceService;

    @GetMapping("")
    public String showCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> userOptional = userService.findUserByUsername(userDetails.getUsername());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Optional<UserBalance> balanceOptional = userBalanceService.findUserByUserID(user.getId());
        UserBalance userBalance = balanceOptional.orElse(new UserBalance());
        model.addAttribute("cart", cartService.getVideocardsInCart());
        model.addAttribute("userBalance", userBalance);
        return "userJSP/cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        String message = cartService.addToCart(id);
        if ("alreadyInCart".equals(message)) {
            redirectAttributes.addFlashAttribute("message", "Видеокарта уже находится в корзине");
            redirectAttributes.addFlashAttribute("messageType", "error");
        } else {
            redirectAttributes.addFlashAttribute("message", "Видеокарта успешно добавлена в корзину");
            redirectAttributes.addFlashAttribute("messageType", "success");
        }
        return "redirect:/videocards";
    }

    @PostMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id") Integer id) {
        cartService.deleteVideocardFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/order")
    public String order(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        cartService.processOrders(userDetails, redirectAttributes);
        return "redirect:/cart";
    }

    @GetMapping("/user/orders")
    public String showOrders(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        cartService.showOrders(userDetails, model);
        return "userJSP/orders";
    }

}
