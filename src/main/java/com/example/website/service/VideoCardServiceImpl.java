package com.example.website.service;

import com.example.website.dao.VideocardRepository;
import com.example.website.entity.Videocard;
import com.example.website.entity.VideocardType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoCardServiceImpl implements VideocardService {
    private final VideocardRepository videocardRepository;

    public VideoCardServiceImpl(VideocardRepository videocardRepository) {
        this.videocardRepository = videocardRepository;
    }

    @Override
    @Transactional
    public List<Videocard> getVideocard() {
        List<Videocard> videocards = videocardRepository.findAll();
        return new ArrayList<>(videocards);
    }

    @Override
    @Transactional
    public Videocard save(Videocard videocard) {
        return videocardRepository.save(videocard);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
       videocardRepository.delete(id);
    }

    @Override
    @Transactional
    public List<Videocard> getVideocardByManufacturer(VideocardType manufacturer) {
        List<Videocard> videocards = videocardRepository.getVideocardByManufacturer(manufacturer);
        return videocards;
    }

     @Override
    @Transactional
    public Videocard findById(Integer id) {
        return videocardRepository.findById(id);
    }

}

