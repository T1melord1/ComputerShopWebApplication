package com.example.website.service.Videocard;

import com.example.website.dao.Videocard.VideocardRepository;
import com.example.website.entity.Videocard.Videocard;
import com.example.website.entity.Videocard.VideocardType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoCardServiceImpl implements VideocardService {

    private final VideocardRepository videocardRepository;

    @Override
    @Transactional
    public List<Videocard> getVideocard() {
        log.debug("Получение списка видеокарт из помидора");
        return videocardRepository.findAll();
    }

    @Override
    @Transactional
    public Videocard save(Videocard videocard) {
        log.debug("Добавление видеокарты в базу данных: {}", videocard);
        return videocardRepository.save(videocard);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        log.debug("Удаление видеокарты с ID: {}", id);
        videocardRepository.delete(id);
    }

    @Override
    @Transactional
    public List<Videocard> getVideocardByManufacturer(VideocardType manufacturer) {
        log.debug("Получение видеокарт от производителя: {}", manufacturer);
        return videocardRepository.getVideocardByManufacturer(manufacturer);
    }

    @Override
    @Transactional
    public Videocard findById(Integer id) {
        log.debug("Поиск видеокарты с ID: {}", id);
        return videocardRepository.findById(id);
    }

}
