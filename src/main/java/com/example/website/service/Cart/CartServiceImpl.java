package com.example.website.service.Cart;

import com.example.website.dao.Videocard.VideocardRepository;
import com.example.website.entity.Videocard.Videocard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final VideocardRepository videocardRepository;
    private final List<Videocard> videocards = Collections.synchronizedList(new ArrayList<>());

    public CartServiceImpl(VideocardRepository videocardRepository) {
        this.videocardRepository = videocardRepository;
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
}