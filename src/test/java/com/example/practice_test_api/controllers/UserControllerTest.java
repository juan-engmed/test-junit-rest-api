package com.example.practice_test_api.controllers;

import com.example.practice_test_api.dto.UserDto;
import com.example.practice_test_api.entities.User;
import com.example.practice_test_api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserServiceImpl userServiceImpl;

    private User user;
    private UserDto userDTO;

    public static final Integer ID      = 1;
    public static final String NAME     = "Juan";
    public static final String EMAIL    = "juan@gmail.com";
    public static final String PASSWORD = "123";

    @BeforeEach
    void setUp() {
        startUser();
    }

    @Test
    void findById() {
        when(userServiceImpl.findById(anyInt())).thenReturn(user);
        when(modelMapper.map(any(), any())).thenReturn(userDTO);

        var response = userController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(UserDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());


    }

    @Test
    void findAll() {
        when(userServiceImpl.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(any(), any())).thenReturn(userDTO);

        var response = userController.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDto.class, response.getBody().get(0).getClass());

        assertEquals(ID, response.getBody().get(0).getId());
        assertEquals(NAME, response.getBody().get(0).getName());
        assertEquals(EMAIL, response.getBody().get(0).getEmail());
        assertEquals(PASSWORD, response.getBody().get(0).getPassword());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }
}