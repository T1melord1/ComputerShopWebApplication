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
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserBalanceService userBalanceService;

    @GetMapping("/register")
    public String showRegistrationForm(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/videocards";
        }
        return "userJSP/register";
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
        // Находим текущий баланс пользователя
        Optional<UserBalance> balanceOptional = userBalanceService.findUserBalanceByUsername(userDetails.getUsername());
        UserBalance existingBalance = balanceOptional.orElseThrow(() -> new RuntimeException("Баланс пользователя не найден"));

        // Обновляем баланс
        log.debug("Пополнение баланса пользователя на: {}", amount);
        BigDecimal newBalance = existingBalance.getBalance().add(amount);
        existingBalance.setBalance(newBalance);
        userBalanceService.updateBalance(existingBalance.getBalance(), existingBalance.getUserId());


        // Добавление сообщения об успешном пополнении
        redirectAttributes.addFlashAttribute("successMessage", "Баланс успешно пополнен на " + amount + " BYN.");
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

        Optional<UserBalance> balanceOptional = userBalanceService.findUserByUserID(user.getId());
        if (balanceOptional.isPresent()) {
            log.debug("Баланс пользователя найден: {}", balanceOptional.get());
        } else {
            log.debug("Баланс пользователя не найден для userId: {}", user.getId());
        }
        UserBalance userBalance = balanceOptional.orElse(new UserBalance());

        model.addAttribute("userProfile", user);
        model.addAttribute("userBalance", userBalance);
        return "videocardJSP/User/userProfile";
    }

    @GetMapping("/user/orders")
    public String showUserOrders(Model model, @AuthenticationPrincipal UserDetails userDetails) {

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
        Optional<User> userOpt = userService.findByResetToken(token);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            model.addAttribute("user", user);
            return "videocardJSP/User/resetPassword"; // Путь к вашей форме изменения пароля
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Неверный или истекший токен.");
            return "redirect:/login";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("user") User user,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                RedirectAttributes redirectAttributes) {
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Пароли не совпадают.");
            return "redirect:/reset-password?token=" + user.getResetToken();
        }

        Optional<User> userOpt = userService.findByResetToken(user.getResetToken());
        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            existingUser.setPassword(passwordEncoder.encode(newPassword));
            existingUser.setResetToken(null); // Очистка токена после успешного сброса пароля
            userService.updateUser(existingUser);
            redirectAttributes.addFlashAttribute("successMessage", "Пароль успешно изменен.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Неверный или истекший токен.");
            return "redirect:/login";
        }
    }

}



