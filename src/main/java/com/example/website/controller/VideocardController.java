package com.example.website.controller;

import com.example.website.entity.Videocard.Videocard;
import com.example.website.entity.Videocard.VideocardType;
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

    @GetMapping("/admin")
    public String getAllVideocards(Model model) {
        List<Videocard> videocards = videocardService.getVideocard();
        model.addAttribute("videocards", videocards);
        return "videocardJSP/Admin/Videocard/videocards";
    }
    @GetMapping("")
    public String getAllVideocardsAdmin(Model model) {
        List<Videocard> videocards = videocardService.getVideocard();
        model.addAttribute("videocards", videocards);
        return "videocardJSP/User/videocardsUser";
    }

    @GetMapping("/admin/add")
    public String showAddForm(Model model) {
        model.addAttribute("videocard", new Videocard());
        return "videocardJSP/Admin/Videocard/add-videocard"; // Возвращает имя представления для формы добавления
    }

    @PostMapping("/admin/add")
    public String addVideocard(@ModelAttribute Videocard videocard) {
        // Объект videocard автоматически заполняется данными из запроса
        videocardService.save(videocard);
        return "redirect:/videocards/admin"; // Перенаправляет на список видеокарт
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteVideocardPost(@PathVariable("id") Integer id) {
        videocardService.delete(id);
        return "redirect:/videocards/admin";
    }

    @GetMapping("/find/manufacturer")
    public String getVideocardsByManufacturer(@RequestParam("manufacturer") VideocardType manufacturer, Model model) {
        List<Videocard> videocards = videocardService.getVideocardByManufacturer(manufacturer);
        model.addAttribute("findVideocards", videocards);
        return "videocardJSP/User/find-videocard";
    }

    @GetMapping("/admin/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Videocard videocard = videocardService.findById(id);
        model.addAttribute("videocard", videocard);
        return "videocardJSP/Admin/Videocard/update-videocard";
    }

    @PostMapping("/admin/update/{id}")
    public String updateVideocard(@ModelAttribute Videocard videocard) {
        videocardService.save(videocard);
        return "redirect:/videocards/admin";
    }
}




