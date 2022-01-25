package com.example.practice_test_api.controllers;

import com.example.practice_test_api.dto.UserDto;
import com.example.practice_test_api.entities.User;
import com.example.practice_test_api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final String ID = "/{id}";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
    return ResponseEntity.ok().body(modelMapper.map(userService.findById(id), UserDto.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok()
                .body(userService.findAll()
                        .stream().map(x -> modelMapper.map(x, UserDto.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                    .buildAndExpand((userService.create(obj).getId())).toUri();
                        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto obj ){

        obj.setId(id);
        User updateObj = userService.update(obj);
        return ResponseEntity.ok().body(modelMapper.map(updateObj, UserDto.class));

    }
}
