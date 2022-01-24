package com.example.practice_test_api.services;

import com.example.practice_test_api.entities.User;


public interface UserService {


    User findById(Integer id);
}
