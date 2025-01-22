package com.example.website.controller;

import com.example.website.entity.User.User;
import com.example.website.entity.User.UserBalance;
import com.example.website.service.Email.EmailService;
import com.example.website.service.User.UserBalanceService;
import com.example.website.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@EnableJpaAuditing
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserBalanceService userBalanceService;
    private final EmailService emailService;

    @GetMapping("/register")
    public String showRegistrationForm(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/videocards";
        }
        return "userJSP/register";
    }

    @PostMapping("/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        return userService.registerUser(user, redirectAttributes);
    }

    @GetMapping("/balance/replenish")
    public String showBalanceReplenishForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserBalance> balanceOptional = userBalanceService.findUserBalanceByUsername(userDetails.getUsername());
        UserBalance existingBalance = balanceOptional.orElseThrow(() -> new RuntimeException("Баланс пользователя не найден"));
        model.addAttribute("userBalance", existingBalance);
        return "videocardJSP/User/balance";
    }

    @PostMapping("/balance/replenish")
    public String balanceReplenish(@RequestParam("amount") BigDecimal amount, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        userService.balanceReplenish(amount, userDetails, redirectAttributes);
        return "redirect:/balance/replenish";
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
        List<UserBalance> userBalances = userBalanceService.findAllBalances();
        model.addAttribute("users", users);
        model.addAttribute("userBalances", userBalances);
        return "videocardJSP/Admin/User/user";
    }


    @GetMapping("/email/confirm")
    public String confirmEmail(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        return userService.confirmEmail(token, redirectAttributes);
    }

    @GetMapping("/user/profile")
    public String showUserProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        userService.showUserProfile(model, userDetails);
        return "videocardJSP/User/userProfile";
    }


    @GetMapping("/password/change/{username}")
    public String changePassword(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> userOptional = userService.findUserByUsername(userDetails.getUsername());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        model.addAttribute("userProfile", user);
        return "videocardJSP/User/changePassword";
    }

    @PostMapping("/password/change/{username}")
    public String changePasswordConfirmation(@RequestParam("newPassword") String newPassword,
                                             @RequestParam("oldPassword") String oldPassword,
                                             @AuthenticationPrincipal UserDetails userDetails,
                                             Model model, @PathVariable("username") String username) {
        Optional<User> userOptional = userService.findUserByUsername(userDetails.getUsername());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(user);
            return "redirect:/user/profile";
        } else {
            model.addAttribute("errorMessage", "Старый пароль неверен");
            return "videocardJSP/User/changePassword";
        }
    }

    @PostMapping("/password/reset")
    public String resetPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            userService.updateUser(user);  // Сохраняем изменения в базе данных

            emailService.sendEmail(user.getEmail(), "Сброс пароля", "Для сброса пароля перейдите по ссылке: http://localhost:8080/reset-password?token=" + token);
            redirectAttributes.addFlashAttribute("successMessage", "Ссылка для смены пароля отправлена. Проверьте вашу почту");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Пользователь с таким email не найден.");
        }
        return "redirect:/login";
    }


    @GetMapping("/password/reset")
    public String showResetForm() {
        return "videocardJSP/User/resetForm";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model, RedirectAttributes redirectAttributes) {
        return userService.showResetPasswordForm(token, model, redirectAttributes);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("user") User user,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                RedirectAttributes redirectAttributes) {
        return userService.userPasswordReset(user, newPassword, confirmPassword, redirectAttributes);
    }
}



