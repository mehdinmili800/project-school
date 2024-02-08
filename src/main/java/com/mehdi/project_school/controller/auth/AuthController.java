package com.mehdi.project_school.controller.auth;

import com.mehdi.project_school.dto.auth.AuthResponse;
import com.mehdi.project_school.dto.auth.LoginRequest;
import com.mehdi.project_school.security.JwtService;
import com.mehdi.project_school.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping("/authenticate")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        String token = authenticateAndGetToken(loginRequest.getUsername(),loginRequest.getPassword());
        return new AuthResponse(token);
    }


    private String authenticateAndGetToken(String username,String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        return jwtService.generate(authentication);
    }
}
