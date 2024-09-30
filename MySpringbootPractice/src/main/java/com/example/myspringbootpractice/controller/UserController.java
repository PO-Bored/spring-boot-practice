package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private String message;



    @RequestMapping("/register")
    public ResponseEntity<User> createUser(@RequestParam @Valid User userRequest){

            Integer id = userService.register(userRequest);
            User user = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @RequestMapping("/login")
    public ResponseEntity<User> login(@RequestParam @Valid UserLogin loginRequest){

            User user = userService.login(loginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
