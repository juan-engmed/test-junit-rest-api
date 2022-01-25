package com.example.practice_test_api.services;

import com.example.practice_test_api.entities.User;

import java.util.List;


public interface UserService {


    User findById(Integer id);

    List<User> findAll();

}
