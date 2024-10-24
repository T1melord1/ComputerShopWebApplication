package com.example.website.service.User;

import com.example.website.dao.User.UserRepository;
import com.example.website.entity.User.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        log.debug("Поиск по имени пользователя: {}", username);
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        log.debug("Поиск всех пользователей в базе данных");
        return userRepository.findAll();
    }
}
