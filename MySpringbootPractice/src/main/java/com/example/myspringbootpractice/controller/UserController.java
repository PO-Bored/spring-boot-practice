package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.dto.ResetPassword;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserEmail;
import com.example.myspringbootpractice.dto.UserLogin;
import com.example.myspringbootpractice.myException.registerExceptionExtend.FailException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody @Valid User userRequest){

        Integer id = userService.register(userRequest);

        User user = userService.getUserById(id);
        Map<String,Object> response = new HashMap<>();
        if(user!=null){
            System.out.println(user.getAccount() + "帳號註冊成功!!!");
            response.put("success",true);
            response.put("message", "註冊成功!!!");
            response.put("user",user.getName());
            System.out.println(user.getName());
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }else{
            System.out.println("註冊失敗");
            throw new FailException();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody @Valid UserLogin loginRequest){

        User user = userService.login(loginRequest);

        Map<String,Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "登入成功");
        response.put("user", user);
        System.out.println(user.getAccount()+" "+user.getPassword());

        return ResponseEntity.ok(response);
    }



    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPa(@RequestBody UserEmail email){
        userService.forgetPassword(email.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body("請在30分鐘內至信箱確認");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPassword resetPassword){
        boolean vaild = userService.validateResetToken(token);
        if(vaild){
            userService.resetPassword(resetPassword);
            return ResponseEntity.status(HttpStatus.OK).body("密碼更改成功");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無效或已過期的 Token");

        }
    }
}
