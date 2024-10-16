package com.example.website.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    @Column(columnDefinition = "enum('USER', 'ADMIN', 'ROOT')")
    @Enumerated(EnumType.STRING)
    private UserType role = UserType.USER;
}
