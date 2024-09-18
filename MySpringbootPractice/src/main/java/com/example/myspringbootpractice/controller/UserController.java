package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.dto.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Valid User userRequest){

        Integer id = userService.createUser(userRequest);
        User user = userService.getUserById(id);
        return ResponseEntity.status(201).body(user);
    }

}
