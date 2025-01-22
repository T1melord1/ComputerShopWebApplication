package com.example.website.service.User;

import com.example.website.dao.User.UserRepository;
import com.example.website.entity.User.User;
import com.example.website.entity.User.UserBalance;
import com.example.website.service.Email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserBalanceService userBalanceService;
    private final EmailService emailService;

    @Override
    @Transactional
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        Optional<User> existingUserEmail = findByEmail(user.getEmail());
        Optional<User> existingUserUsername = findUserByUsername(user.getUsername());
        if (existingUserEmail.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Пользователь с таким почтовым адресом уже существует");
            return "redirect:/register";
        } else if (existingUserUsername.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Пользователь с таким именем уже существует");
            return "redirect:/register";
        } else {
            // Хэшируем пароль пользователя
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // Генерируем токен подтверждения
            String confirmationToken = UUID.randomUUID().toString();
            user.setConfirmationToken(confirmationToken);

            // Сохраняем пользователя
            userRepository.save(user);

            // Создаем начальный баланс для пользователя
            userBalanceService.createBalance(user.getId());

            // Отправляем email подтверждения
            emailService.sendEmail(user.getEmail(), "Подтверждение регистрации",
                    "Спасибо за регистрацию. Пожалуйста, подтвердите ваш email перейдя по адресу http://localhost:8080/email/confirm?token=" + user.getConfirmationToken());

            redirectAttributes.addFlashAttribute("successMessage", "Регистрация прошла успешно! Проверьте вашу почту для подтверждения.");
            log.debug("Добавление пользователя: {}", user);
            return "redirect:/login";
        }
    }


    @Override
    public Optional<User> findUserByUsername(String username) {
        log.debug("Поиск по имени пользователя: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        log.debug("Поиск всех пользователей в базе данных");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByConfirmationToken(String token) {
        log.debug("Поиск пользователя по токену подтверждения: {}", token);
        return userRepository.findByConfirmationToken(token);
    }

    @Override
    public Optional<User> findByResetToken(String token) {
        log.debug("Поиск пользователя по токену ресета: {}", token);
        return userRepository.findByResetToken(token);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.debug("Поиск почты пользователя: {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        log.debug("Обновление пользователя: {}", user);
        userRepository.save(user);
    }

    @Override
    public void balanceReplenish(BigDecimal amount, UserDetails userDetails, RedirectAttributes redirectAttributes) {
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
    }

    @Override
    @Transactional
    public String confirmEmail(String token, RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = findByConfirmationToken(token);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getConfirmationToken() == null) {  // Проверка на уже подтверждённого пользователя
                redirectAttributes.addFlashAttribute("infoMessage", "Email уже был подтверждён.");
                return "redirect:/login";
            }
            user.setConfirmationToken(null);  // Сброс токена после подтверждения
            updateUser(user);
            log.debug("Пользователь {} подтвердил email", user.getUsername());
            redirectAttributes.addFlashAttribute("successMessage", "Email подтверждён. Вы можете войти.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Неверный токен подтверждения.");
        }
        return "redirect:/login";
    }

    @Override
    public void showUserProfile(Model model, UserDetails userDetails) {
        Optional<User> userOptional = findUserByUsername(userDetails.getUsername());
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
    }

    @Override
    public String showResetPasswordForm(String token, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = findByResetToken(token);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            model.addAttribute("user", user);
            return "videocardJSP/User/resetPassword"; // Путь к вашей форме изменения пароля
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Неверный или истекший токен.");
            return "redirect:/login";
        }
    }

    @Override
    @Transactional
    public String userPasswordReset(User user, String newPassword, String confirmPassword, RedirectAttributes redirectAttributes) {
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Пароли не совпадают.");
            return "redirect:/reset-password?token=" + user.getResetToken();
        }
        Optional<User> userOpt = findByResetToken(user.getResetToken());
        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            existingUser.setPassword(passwordEncoder.encode(newPassword));
            existingUser.setResetToken(null); // Очистка токена после успешного сброса пароля
            updateUser(existingUser);
            redirectAttributes.addFlashAttribute("successMessage", "Пароль успешно изменен.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Неверный или истекший токен.");
        }
        return "userJSP/login";

    }
}


