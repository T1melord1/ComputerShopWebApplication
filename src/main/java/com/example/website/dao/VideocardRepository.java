package com.example.website.dao;

import com.example.website.entity.Videocard;

import java.util.List;

public interface VideocardRepository {
    List<Videocard> findAll();

    Videocard save(Videocard videocard);

    void delete(Integer id);

    Videocard getVideocardById(Integer id);
}
