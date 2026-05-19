package com.MosIC.MosIC_Office.auth.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {


  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "gmail", nullable = false, unique = true, length = 200)
    private String gmail;

    @Column(name = "contact", length = 20)
    private String contact;

    // BCrypt hash — never stored as plain text
    @Column(name = "password", nullable = false, length = 300)
    private String password;

    // 1 = active, 0 = disabled
    @Column(name = "status")
    private Integer status = 1;
    //  ADDED: Role of this user — "user" | "superuser" | "admin"
    // Defaults to "user" if not specified during registration
    @Column(name = "profile", nullable = false, length = 20)
    private String profile = "user";
}
