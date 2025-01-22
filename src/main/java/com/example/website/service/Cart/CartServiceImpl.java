package com.example.website.service.Cart;

import com.example.website.dao.Cart.CartRepository;
import com.example.website.dao.Videocard.VideocardRepository;
import com.example.website.entity.User.Orders;
import com.example.website.entity.User.User;
import com.example.website.entity.User.UserBalance;
import com.example.website.entity.Videocard.Videocard;
import com.example.website.service.User.UserBalanceService;
import com.example.website.service.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final VideocardRepository videocardRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final UserBalanceService userBalanceService;
    private final List<Videocard> videocards = Collections.synchronizedList(new ArrayList<>());

    public CartServiceImpl(VideocardRepository videocardRepository, CartRepository cartRepository, UserService userService, UserBalanceService userBalanceService) {
        this.videocardRepository = videocardRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.userBalanceService = userBalanceService;
    }

    @Override
    public List<Videocard> getVideocardsInCart() {
        log.debug("Получение видеокарт в корзине");
        return videocards;
    }


    @Override
    @Transactional
    public String addToCart(Integer id) {
        log.debug("Добавление видеокарты с ID {} в корзину", id);
        // Найти видеокарту по ID
        Videocard videocard = videocardRepository.findById(id);

        // Проверка на наличие видеокарты в корзине
        boolean alreadyInCart = videocards.stream()
                .anyMatch(v -> v.getId().equals(id));
        if (alreadyInCart) {
            log.debug("Видеокарта с ID {} уже находится в корзине", id);
            return "alreadyInCart"; // Если видеокарта уже в корзине, не добавляем её повторно
        }

        // Добавление видеокарты в корзину
        videocards.add(videocard);
        log.debug("Видеокарта с ID {} успешно добавлена в корзину", id);
        return "success";
    }


    @Override
    @Transactional
    public void deleteVideocardFromCart(Integer id) {
        log.debug("Удаление видеокарты с ID {} из корзины", id);
        videocards.removeIf(videocard -> videocard.getId().equals(id));
    }

    @Override
    public void clearCart() {
        log.debug("Очистка корзины");
        videocards.clear();
    }

    @Override
    @Transactional
    public void saveOrders(Orders orders) {
        log.debug("Сохранение заказа в БД: {}", orders);
        cartRepository.saveOrders(orders);
    }

    @Override
    public List<Orders> findAllOrders(UUID userId) {
        log.debug("Поиск всех заказов пользователя в базе данных c ID: {}", userId);
        return cartRepository.findAllOrders(userId);
    }

    @Override
    @Transactional
    public void processOrders(UserDetails userDetails, RedirectAttributes redirectAttributes) {
        log.debug("Обработка заказа для пользователя: {}", userDetails.getUsername());

        User user = userService.findUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        UserBalance userBalance = userBalanceService.findUserByUserID(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Баланс пользователя не найден"));

        List<Videocard> videocardsList = getVideocardsInCart();
        if (videocardsList.isEmpty()) {
            log.debug("Корзина пуста для пользователя {}", userDetails.getUsername());
            redirectAttributes.addFlashAttribute("errorMessage", "Корзина пуста");
            return;
        }

        BigDecimal totalPrice = BigDecimal.valueOf(videocardsList.stream().mapToDouble(Videocard::getPrice).sum());
        log.debug("Общая стоимость заказа: {}", totalPrice);

        if (userBalance.getBalance().compareTo(totalPrice) < 0) {
            log.debug("Недостаточно средств на балансе: {} < {}", userBalance.getBalance(), totalPrice);
            redirectAttributes.addFlashAttribute("errorMessage", "Недостаточно средств на балансе");
            return;
        }

        BigDecimal newBalance = userBalance.getBalance().subtract(totalPrice);
        userBalanceService.updateBalance(newBalance, userBalance.getUserId());
        log.debug("Новый баланс после заказа для пользователя {}: {}", userDetails.getUsername(), newBalance);

        String videocards = videocardsList.stream()
                .map(vc -> vc.getManufacturer() + " " + vc.getGraphicProcessor() + " <strong>" + vc.getPrice() + "</strong> BYN")
                .collect(Collectors.joining(", "));

        Orders orders = new Orders();
        orders.setOrderDate(LocalDateTime.now());
        orders.setVideocards(videocards);
        orders.setTotalPrice(totalPrice);
        orders.setUser(user);

        saveOrders(orders);
        for (Videocard videocard : videocardsList) {
            videocardRepository.delete(videocard.getId());
        }
        clearCart();

        log.debug("Заказ успешно оформлен для пользователя {}", userDetails.getUsername());
        redirectAttributes.addFlashAttribute("successMessage", "Заказ успешно оформлен!");
    }

    @Override
    public void showOrders(UserDetails userDetails, Model model) {
        Optional<User> userOptional = userService.findUserByUsername(userDetails.getUsername());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        List<Orders> orders = findAllOrders(user.getId());
        model.addAttribute("orders", orders);
    }


}


