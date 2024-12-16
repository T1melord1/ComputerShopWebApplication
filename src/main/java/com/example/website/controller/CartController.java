package com.example.website.controller;

import com.example.website.entity.User.User;
import com.example.website.entity.User.UserBalance;
import com.example.website.entity.Videocard.Videocard;
import com.example.website.service.Cart.CartService;
import com.example.website.service.User.UserBalanceService;
import com.example.website.service.User.UserService;
import com.example.website.service.Videocard.VideocardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final UserBalanceService userBalanceService;
    private final VideocardService videocardService;

    @GetMapping("")
    public String showCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> userOptional = userService.findUserByUsername(userDetails.getUsername());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Optional<UserBalance> balanceOptional = userBalanceService.findUserByUserID(user.getId());
        UserBalance userBalance = balanceOptional.orElse(new UserBalance());
        model.addAttribute("cart", cartService.getVideocardsInCart());
        model.addAttribute("userBalance", userBalance);
        return "videocardJSP/User/cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        String message = cartService.addToCart(id);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/videocards";
    }

    @PostMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id") Integer id) {
        cartService.deleteVideocardFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/order")
    @Transactional
    public String order(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        // Находим пользователя по username
        Optional<User> userOptional = userService.findUserByUsername(userDetails.getUsername());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        // Находим баланс пользователя по userId
        Optional<UserBalance> balanceOptional = userBalanceService.findUserByUserID(user.getId());
        UserBalance userBalance = balanceOptional.orElseThrow(() -> new RuntimeException("Баланс пользователя не найден"));

        // Получаем видеокарты из корзины и рассчитываем общую стоимость
        List<Videocard> videocards = cartService.getVideocardsInCart();
        BigDecimal totalPrice = BigDecimal.valueOf(videocards.stream().mapToDouble(Videocard::getPrice).sum());

        // Проверяем, достаточно ли средств на балансе
        if (userBalance.getBalance().compareTo(totalPrice) < 0) {
            String message = "Недостаточно средств на балансе";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/cart";
        }

        // Вычитаем стоимость товаров в корзине из баланса пользователя
        BigDecimal newBalance = userBalance.getBalance().subtract(totalPrice);
        userBalanceService.updateBalance(newBalance, userBalance.getUserId());

        // Удаляем видеокарты из базы данных
        List<Integer> videoCardIds = videocards.stream()
                .map(Videocard::getId)
                .collect(Collectors.toList());
        videoCardIds.forEach(videocardService::delete);

        // Очищаем корзину
        cartService.clearCart();

        return "redirect:/cart";
    }


}
