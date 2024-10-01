package com.example.myspringbootpractice.dto;

import jakarta.validation.constraints.Email;

public class UserEmail {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
