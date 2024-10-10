package com.example.website.service.Videocard;

import com.example.website.dao.VideocardRepository;
import com.example.website.entity.Videocard;
import com.example.website.entity.VideocardType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoCardServiceImpl implements VideocardService {
    private static final Logger logger = LoggerFactory.getLogger(VideoCardServiceImpl.class);
    @Autowired
    private final VideocardRepository videocardRepository;

    public VideoCardServiceImpl(VideocardRepository videocardRepository) {
        this.videocardRepository = videocardRepository;
    }

    @Override
    @Transactional
    public List<Videocard> getVideocard() {
        logger.debug("Получение списка видеокарт из БД");
        List<Videocard> videocards = videocardRepository.findAll();
        return new ArrayList<>(videocards);
    }

    @Override
    @Transactional
    public Videocard save(Videocard videocard) {
        logger.debug("Добавление видеокарты в базу данных: {}", videocard);
        return videocardRepository.save(videocard);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        logger.debug("Удаление видеокарты с ID: {}", id);
        videocardRepository.delete(id);
    }

    @Override
    @Transactional
    public List<Videocard> getVideocardByManufacturer(VideocardType manufacturer) {
        logger.debug("Получение видеокарт от производителя: {}", manufacturer);
        return videocardRepository.getVideocardByManufacturer(manufacturer);
    }

    @Override
    @Transactional
    public Videocard findById(Integer id) {
        logger.debug("Поиск видеокарты с ID: {}", id);
        return videocardRepository.findById(id);
    }
}
