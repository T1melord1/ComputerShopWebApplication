package com.example.website.dao;

import com.example.website.entity.Videocard;
import com.example.website.entity.VideocardType;

import java.util.List;

public interface VideocardRepository {
    List<Videocard> findAll();

    Videocard save(Videocard videocard);

    void delete(Integer id);

    List<Videocard> getVideocardByManufacturer(VideocardType manufacturer);

    Videocard findById(Integer id);
}
