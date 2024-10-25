package com.example.website.service.User;

import com.example.website.dao.User.UserRepository;
import com.example.website.entity.User.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(User user) {
        log.debug("Добавление пользователя: {}", user);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);
        userRepository.save(user);
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
}


