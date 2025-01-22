package com.example.website.service.User;

import com.example.website.entity.User.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    String registerUser(User user, RedirectAttributes redirectAttributes);

    List<User> findAllUsers();

    Optional<User> findUserByUsername(String username);

    Optional<User> findByConfirmationToken(String token);

    Optional<User> findByResetToken(String token);

    Optional<User> findByEmail(String email);

    void updateUser(User user);

    void balanceReplenish(BigDecimal amount, UserDetails userDetails, RedirectAttributes redirectAttributes);

    String confirmEmail(String token, RedirectAttributes redirectAttributes);

    void showUserProfile(Model model, UserDetails userDetails);

    String showResetPasswordForm(String token, Model model, RedirectAttributes redirectAttributes);

    String userPasswordReset(User user, String newPassword, String confirmPassword, RedirectAttributes redirectAttributes);
}
