package com.example.website.controller;

import com.example.website.entity.Videocard;
import com.example.website.entity.VideocardType;
import com.example.website.service.Videocard.VideocardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/videocards")
@RequiredArgsConstructor
public class VideocardController {

    private final VideocardService videocardService;

    @GetMapping("")
    public String getAllVideocards(Model model) {
        List<Videocard> videocards = videocardService.getVideocard();
        model.addAttribute("videocards", videocards);
        return "videocardJSP/videocardsJSP";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("videocard", new Videocard());
        return "videocardJSP/add-videocard"; // Возвращает имя представления для формы добавления
    }

    @PostMapping("/add")
    public String addVideocard(@ModelAttribute Videocard videocard) {
        // Объект videocard автоматически заполняется данными из запроса
        videocardService.save(videocard);
        return "redirect:/videocards"; // Перенаправляет на список видеокарт
    }

    @PostMapping("/delete/{id}")
    public String deleteVideocardPost(@PathVariable Integer id) {
        videocardService.delete(id);
        return "redirect:/videocards";
    }

    @GetMapping("/find/manufacturer")
    public String getVideocardsByManufacturer(@RequestParam VideocardType manufacturer, Model model) {
        List<Videocard> videocards = videocardService.getVideocardByManufacturer(manufacturer);
        model.addAttribute("findVideocards", videocards);
        return "videocardJSP/find-videocard";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Videocard videocard = videocardService.findById(id);
        model.addAttribute("videocard", videocard);
        return "videocardJSP/update-videocard";
    }

    @PostMapping("/update/{id}")
    public String updateVideocard(@ModelAttribute Videocard videocard) {
        videocardService.save(videocard);
        return "redirect:/videocards";
    }
}




