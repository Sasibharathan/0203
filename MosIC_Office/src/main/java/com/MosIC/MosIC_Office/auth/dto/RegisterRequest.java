package com.MosIC.MosIC_Office.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Gmail is required")
    @Email(message = "Must be a valid email address")
    private String gmail;

    // Optional — no constraint
    private String contact;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    // Optional — defaults to 1 in controller if not provided
    private Integer status;

    // Optional — defaults to "user" in controller if not provided
    @Pattern(regexp = "user|superuser|admin",
             message = "Profile must be one of: user, superuser, admin")
    private String profile;
}