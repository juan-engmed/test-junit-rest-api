package com.example.practice_test_api.services.impl;

import com.example.practice_test_api.entities.User;
import com.example.practice_test_api.repositories.UserRepository;
import com.example.practice_test_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElse(null);
    }
}
