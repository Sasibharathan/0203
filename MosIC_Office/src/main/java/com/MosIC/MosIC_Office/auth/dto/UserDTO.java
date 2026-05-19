package com.MosIC.MosIC_Office.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String gmail;
    private String contact;
    private Integer status;
    private String profile;//expose the profile/role to the client
}
