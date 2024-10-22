package com.example.website.service.User;

import com.example.website.dao.User.UserRepository;
import com.example.website.entity.User.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Поиск пользователя в базе данных по имени пользователя {}",username);
        // Поиск пользователя в базе данных по username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        // Преобразование роли пользователя в SimpleGrantedAuthority
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole());

        // Здесь используем UserDetails из Spring Security (org.springframework.security.core.userdetails.User)
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                Collections.singletonList(grantedAuthority));  // одна роль, упакованная в список
    }
}

