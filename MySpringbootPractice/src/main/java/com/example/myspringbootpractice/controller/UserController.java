package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.dto.ResetPassword;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserEmail;
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


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Valid User userRequest){

            Integer id = userService.register(userRequest);
            User user = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLogin loginRequest){

            User user = userService.login(loginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPa(@RequestBody UserEmail email){
        userService.getUserByEmail(email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body("請至信箱確認");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> getUserById(@RequestBody ResetPassword resetPassword){
        userService.getUserByEmail(resetPassword.getEmail());
        userService.resetPassword(resetPassword);
        return ResponseEntity.status(HttpStatus.OK).body("密碼更改成功");

    }
}
