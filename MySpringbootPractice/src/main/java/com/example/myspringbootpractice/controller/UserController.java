package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private String message;



    @RequestMapping(path="/register",method={RequestMethod.GET,RequestMethod.POST})
    public String createUser(User userRequest, Model model, HttpServletRequest request){
        if(request.getMethod().equals("POST")){
            Integer id = userService.register(userRequest);
            User user = userService.getUserById(id);
            message="帳號建立成功";
            model.addAttribute("message", message);
        }

        return "register";
    }

    @RequestMapping(path="/login",method={RequestMethod.GET,RequestMethod.POST})
    public String login(UserLogin loginRequest,Model model,HttpServletRequest request){

        if(request.getMethod().equals("POST")){
            User user = userService.login(loginRequest);
            message="登入成功";
            model.addAttribute("message", message);
        }


        return "login";
    }

}
