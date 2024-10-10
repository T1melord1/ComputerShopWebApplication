package com.example.website.service.Cart;

import com.example.website.dao.VideocardRepository;
import com.example.website.entity.Videocard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final VideocardRepository videocardRepository;
    private final List<Videocard> videocards = Collections.synchronizedList(new ArrayList<>());

    public CartServiceImpl(VideocardRepository videocardRepository) {
        this.videocardRepository = videocardRepository;
    }

    @Override
    @Transactional
    public List<Videocard> getVideocardsInCart() {
        logger.debug("Получение видеокарт в корзине");
        return videocards;
    }


    @Override
    @Transactional
    public void addToCart(Integer id) {
        logger.debug("Добавление видеокарты с ID {} в корзину", id);
        Videocard videocard = videocardRepository.findById(id);
        videocards.add(videocard);
    }

    @Override
    @Transactional
    public void deleteVideocardFromCart(Integer id) {
        logger.debug("Удаление видеокарты с ID {} из корзины", id);
        videocards.removeIf(videocard -> videocard.getId().equals(id));
    }
}