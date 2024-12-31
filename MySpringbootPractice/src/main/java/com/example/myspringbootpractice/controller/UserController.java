package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.config.JwtUtil;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;
import com.example.myspringbootpractice.myException.registerExceptionExtend.FailException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
//@CrossOrigin 跨域請求
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
    public ResponseEntity<Map<String,Object>> login(@RequestBody @Valid UserLogin loginRequest,HttpServletResponse httpResponse){

        User user = userService.login(loginRequest); // 認證用戶
        String token = JwtUtil.generateToken(user.getId(),user.getName());
        ResponseCookie cookie = ResponseCookie.from("authToken",token)
                .httpOnly(false)  //讓javaScript讀取
                .secure(false)    //支持http傳輸
                .path("/")        //適用於所有域名
                .maxAge(Duration.ofHours(1))
                //.sameSite("None")
                .build();
        httpResponse.setHeader("Set-Cookie",cookie.toString());

        System.out.println("cookie傳遞完成");
        System.out.println(cookie);

        Map<String,Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "登入成功");
        response.put("user", user);
        System.out.println(user.getAccount()+" "+user.getPassword());

        return ResponseEntity.ok(response);
    }

    //測試前端是否正確接收到cookie
    @GetMapping("/protected-resource")
    public ResponseEntity<String> getProtectedResource(@CookieValue("authToken") String token) {
        Claims claims = JwtUtil.validateToken(token); // 驗證 JWT
        String username = claims.get("username", String.class);
        return ResponseEntity.ok("Hello, " + username);
    }
}
