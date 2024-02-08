package com.mehdi.project_school.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
