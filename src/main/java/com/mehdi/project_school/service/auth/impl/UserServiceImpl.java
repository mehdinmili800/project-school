package com.mehdi.project_school.service.auth.impl;

import com.mehdi.project_school.entity.user.User;

import com.mehdi.project_school.repository.user.UserRepository;
import com.mehdi.project_school.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;



    @Override
    public User save(User user) {
       return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
