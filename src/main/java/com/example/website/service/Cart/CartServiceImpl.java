package com.example.website.service.Cart;

import com.example.website.dao.VideocardRepository;
import com.example.website.entity.Videocard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final VideocardRepository videocardRepository;

    private final List<Videocard> videocards = new ArrayList<>();

    public CartServiceImpl(VideocardRepository videocardRepository) {
        this.videocardRepository = videocardRepository;
    }

    @Override
    @Transactional
    public List<Videocard> getVideocardsInCart() {
        return videocards;
    }


    @Override
    @Transactional
    public void addToCart(Integer id) {
        Videocard videocard = videocardRepository.findById(id);
        videocards.add(videocard);
    }

    @Override
    @Transactional
    public void deleteVideocardFromCart(Integer id) {
        videocards.removeIf(videocard -> videocard.getId().equals(id));
    }
}