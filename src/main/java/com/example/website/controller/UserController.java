package com.example.website.controller;

import com.example.website.entity.User.User;
import com.example.website.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "userJSP/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "userJSP/login";
    }

    @PostMapping("/login")
    public String addUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/user/videocards";
    }


}
