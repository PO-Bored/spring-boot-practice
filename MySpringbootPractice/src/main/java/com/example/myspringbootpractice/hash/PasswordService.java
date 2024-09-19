package com.example.myspringbootpractice.hash;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String hashPassword(String password) {
        return encoder.encode(password);
    }
    public boolean matchPassword(String password, String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }
}
