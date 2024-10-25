package com.example.website.entity.User;


import lombok.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    @Column(columnDefinition = "enum('USER', 'ADMIN', 'ROOT')", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType role = UserType.USER;
    @Getter
    @Setter
    private String confirmationToken;
}
