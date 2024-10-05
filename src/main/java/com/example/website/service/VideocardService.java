package com.example.website.service;

import com.example.website.entity.Videocard;
import com.example.website.entity.VideocardType;

import java.util.List;

public interface VideocardService {
    List<Videocard> getVideocard();

    Videocard save(Videocard videocard);

    void delete(Integer id);

    List<Videocard> getVideocardByManufacturer(VideocardType manufacturer);
}
