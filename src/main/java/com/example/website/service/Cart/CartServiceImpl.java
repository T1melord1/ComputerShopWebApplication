package com.example.website.service.Cart;

import com.example.website.dao.VideocardRepository;
import com.example.website.entity.Videocard;
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
    @Transactional
    public List<Videocard> getVideocardsInCart() {
        log.debug("Получение видеокарт в корзине");
        return videocards;
    }


    @Override
    @Transactional
    public void addToCart(Integer id) {
        log.debug("Добавление видеокарты с ID {} в корзину", id);
        Videocard videocard = videocardRepository.findById(id);
        videocards.add(videocard);
    }

    @Override
    @Transactional
    public void deleteVideocardFromCart(Integer id) {
        log.debug("Удаление видеокарты с ID {} из корзины", id);
        videocards.removeIf(videocard -> videocard.getId().equals(id));
    }
}