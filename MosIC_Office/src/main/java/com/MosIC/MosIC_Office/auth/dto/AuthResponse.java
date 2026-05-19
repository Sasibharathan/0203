package com.MosIC.MosIC_Office.auth.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private String gmail;
    private String contact;
    private Integer status;
    private String profile;
    // ADDED: Returned on login so the frontend knows the logged-in user's role
    // The frontend reads this and stores it (e.g. localStorage) to show/hide buttons


}