package com.mehdi.project_school.service.auth;

import com.mehdi.project_school.entity.user.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> getUserByUsername(String username);
}
