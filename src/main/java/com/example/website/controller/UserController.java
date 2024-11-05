package com.example.website.controller;

import com.example.website.entity.User.User;
import com.example.website.service.Email.EmailService;
import com.example.website.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    @GetMapping("/register")
    public String showRegistrationForm(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/videocards";
        }
        return "userJSP/register";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/videocards";
        }
        return "userJSP/login";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "videocardJSP/Admin/User/user";
    }

    @PostMapping("/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        Optional<User> existingUserEmail = userService.findByEmail(user.getEmail());
        Optional<User> existingUserUsername = userService.findUserByUsername(user.getUsername());
        if (existingUserEmail.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Пользователь с таким почтовым адресом уже существует");
            return "redirect:/register";
        } else if (existingUserUsername.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Пользователь с таким именем уже существует");
            return "redirect:/register";
        } else {
            userService.registerUser(user);
            emailService.sendEmail(user.getEmail(), "Подтверждение регистрации",
                    "Спасибо за регистрацию. Пожалуйста, подтвердите ваш email перейдя по адресу http://localhost:8080/email/confirm?token=" + user.getConfirmationToken());
            redirectAttributes.addFlashAttribute("successMessage", "Регистрация прошла успешно! Проверьте вашу почту для подтверждения.");
            return "redirect:/login";
        }
    }


    @GetMapping("/email/confirm")
    public String confirmEmail(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userService.findByConfirmationToken(token);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getConfirmationToken() == null) {  // Проверка на уже подтверждённого пользователя
                redirectAttributes.addFlashAttribute("infoMessage", "Email уже был подтверждён.");
                return "redirect:/login";
            }
            user.setConfirmationToken(null);  // Сброс токена после подтверждения
            userService.updateUser(user);
            log.debug("Пользователь {} подтвердил email", user.getUsername());
            redirectAttributes.addFlashAttribute("successMessage", "Email подтверждён. Вы можете войти.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Неверный токен подтверждения.");
        }
        return "redirect:/login";
    }

    @GetMapping("/user/profile")
    public String showUserProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> userOptional = userService.findUserByUsername(userDetails.getUsername());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        model.addAttribute("userProfile", user);
        return"videocardJSP/User/userProfile";
    }
}

