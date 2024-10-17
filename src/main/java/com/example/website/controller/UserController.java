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
    public String login(@ModelAttribute("user") User user, Model model) {
        User loggedInUser = userService.authenticate(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            // Логика для успешного логина, например, установка сессии
            model.addAttribute("user", loggedInUser);
            return "redirect:/videocards"; // Перенаправление на главную страницу
        } else {
            // Логика для неуспешного логина
            model.addAttribute("error", "Неверный логин или пароль.");
            return "userJSP/login"; // Возвращаем на страницу логина
        }
    }
}
