package com.example.website.controller;

import com.example.website.entity.Videocard;
import com.example.website.entity.VideocardType;
import com.example.website.service.VideocardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/videocards")
public class VideocardController {

    @Autowired
    private VideocardService videocardService;

    @GetMapping("")
    public String getAllVideocards(Model model) {
        List<Videocard> videocards = videocardService.getVideocard();
        model.addAttribute("videocards", videocards);
        return "videocardsJSP";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("videocard", new Videocard());
        return "add-videocard"; // Возвращает имя представления для формы добавления
    }

    @PostMapping("/add")
    public String addVideocard(@ModelAttribute Videocard videocard) {
        // Объект videocard автоматически заполняется данными из запроса
        videocardService.save(videocard);
        return "redirect:/videocards"; // Перенаправляет на список видеокарт
    }

    @PostMapping("/delete/{id}")
    public String deleteVideocardPost(@PathVariable Integer id) {
        return deleteVideocard(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVideocard(@PathVariable Integer id) {
        videocardService.delete(id);
        return "redirect:/videocards";
    }

    @GetMapping("/find/manufacturer/{manufacturer}")
    public String getVideocardByManufacturer(@PathVariable VideocardType manufacturer, Model model) {
        List<Videocard> videocard = videocardService.getVideocardByManufacturer(manufacturer);
        model.addAttribute("findVideocards", videocard);
        return "find-videocard";
    }

}




