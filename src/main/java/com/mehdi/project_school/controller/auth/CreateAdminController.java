package com.mehdi.project_school.controller.auth;

import com.mehdi.project_school.dto.auth.AuthResponse;
import com.mehdi.project_school.dto.auth.LoginRequest;
import com.mehdi.project_school.dto.auth.SignUpRequest;
import com.mehdi.project_school.entity.user.User;
import com.mehdi.project_school.entity.user.UserRoleName;
import com.mehdi.project_school.security.JwtService;
import com.mehdi.project_school.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class CreateAdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;






    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup-admin")
    public AuthResponse signUp(@RequestBody SignUpRequest signUpRequest){
        userService.save(mapSignUpRequestToUser(signUpRequest));
        String token = authenticateAndGetToken(signUpRequest.getUsername(),signUpRequest.getPassword());
        return new AuthResponse(token);

    }

    private String authenticateAndGetToken(String username,String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        return jwtService.generate(authentication);
    }

    private User mapSignUpRequestToUser(SignUpRequest signUpRequest){
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setFullName(signUpRequest.getFullName());
        user.setRole(UserRoleName.ROLE_ADMIN);
        return user;
    }
}
