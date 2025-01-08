package com.example.website.controller;

import com.example.website.entity.User.Orders;
import com.example.website.entity.User.User;
import com.example.website.entity.User.UserBalance;
import com.example.website.entity.Videocard.Videocard;
import com.example.website.service.Cart.CartService;
import com.example.website.service.User.UserBalanceService;
import com.example.website.service.User.UserService;
import jakarta.transaction.Transactional;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        return "videocardJSP/User/cart";
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
    @Transactional
    public String order(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        log.debug("Обработка заказа для пользователя: {}", userDetails.getUsername());

        Optional<User> userOptional = userService.findUserByUsername(userDetails.getUsername());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Optional<UserBalance> balanceOptional = userBalanceService.findUserByUserID(user.getId());

        UserBalance userBalance = balanceOptional.orElseThrow(() -> new UsernameNotFoundException("Баланс пользователя не найден"));
        log.debug("Текущий баланс для пользователя {}: {}", userDetails.getUsername(), userBalance.getBalance());

        List<Videocard> videocardsList = cartService.getVideocardsInCart();
        if (videocardsList.isEmpty()) {
            log.debug("Корзина пуста для пользователя {}", userDetails.getUsername());
            redirectAttributes.addFlashAttribute("errorMessage", "Корзина пуста");
            return "redirect:/cart";
        }

        BigDecimal totalPrice = BigDecimal.valueOf(videocardsList.stream().mapToDouble(Videocard::getPrice).sum());
        log.debug("Общая стоимость заказа: {}", totalPrice);

        if (userBalance.getBalance().compareTo(totalPrice) < 0) {
            log.debug("Недостаточно средств на балансе: {} < {}", userBalance.getBalance(), totalPrice);
            redirectAttributes.addFlashAttribute("errorMessage", "Недостаточно средств на балансе");
            return "redirect:/cart";
        }

        BigDecimal newBalance = userBalance.getBalance().subtract(totalPrice);
        log.debug("Новый баланс после заказа для пользователя {}: {}", userDetails.getUsername(), newBalance);
        userBalanceService.updateBalance(newBalance, userBalance.getUserId());

        // Объединение названий видеокарт в одну строку с жирным выделением цены
        String videocards = videocardsList.stream()
                .map(vc -> vc.getManufacturer() + " " + vc.getGraphicProcessor() + " <strong>" + vc.getPrice() + "</strong>" + " BYN")
                .collect(Collectors.joining(", "));

        Orders orders = new Orders();
        orders.setOrderDate(LocalDateTime.now());
        orders.setVideocards(videocards); // Сохранение объединенных названий видеокарт с жирной ценой
        orders.setTotalPrice(totalPrice);
        orders.setUser(user);

        userService.saveOrders(orders); // Сохраняем заказ в базе данных

        cartService.clearCart();

        log.debug("Заказ успешно оформлен для пользователя {}", userDetails.getUsername());
        redirectAttributes.addFlashAttribute("successMessage", "Заказ успешно оформлен!");
        return "redirect:/cart";
    }



}
