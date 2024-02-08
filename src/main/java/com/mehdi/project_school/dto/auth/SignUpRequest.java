package com.mehdi.project_school.dto.auth;


import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String fullName;
}
