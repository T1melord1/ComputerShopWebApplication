package com.example.website.service.Videocard;

import com.example.website.entity.Videocard.Videocard;
import com.example.website.entity.Videocard.VideocardType;

import java.util.List;

public interface VideocardService {
    List<Videocard> getVideocard();

    Videocard save(Videocard videocard);

    void delete(Integer id);

    List<Videocard> getVideocardByManufacturer(VideocardType manufacturer);

    Videocard findById(Integer id);
}
