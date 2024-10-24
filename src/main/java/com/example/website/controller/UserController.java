package com.example.website.controller;

import com.example.website.entity.User.User;
import com.example.website.service.Email.EmailService;
import com.example.website.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final EmailService emailService;

    @GetMapping("/register")
    public String showRegistrationForm(HttpServletRequest request) {
        if(request.getUserPrincipal() != null) {
            return "redirect:/videocards";
        }
        return "userJSP/register";  // Возвращает страницу register.jsp
    }

    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request) {
        if(request.getUserPrincipal() != null) {
            return "redirect:/videocards";
        }
        return "userJSP/login";  // Возвращает страницу login.jsp
    }

    @PostMapping("/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        userService.registerUser(user);
        emailService.sendEmail(user.getEmail(), "Подтверждение регистрации",
                "Спасибо за регистрацию. Пожалуйста, подтвердите ваш email.");
        redirectAttributes.addFlashAttribute("successMessage", "Регистрация прошла успешно! Проверьте вашу почту для подтверждения.");
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "videocardJSP/Admin/User/user";
    }
}
