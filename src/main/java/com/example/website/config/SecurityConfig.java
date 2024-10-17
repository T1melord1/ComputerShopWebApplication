package com.example.website.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/register", "/user/login").permitAll() // Разрешить доступ к страницам регистрации и логина
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin((form) -> form
                        .loginPage("/user/login") // Указание страницы для логина
                        .defaultSuccessUrl("/videocards") // Перенаправление после успешного входа
                        .failureUrl("/user/login?error=true") // Перенаправление при ошибке входа
                        .permitAll() // Разрешить всем доступ к форме входа
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/user/login?logout=true") // Перенаправление после выхода
                        .permitAll() // Разрешить всем доступ к выходу
                );

        return http.build();
    }
}
