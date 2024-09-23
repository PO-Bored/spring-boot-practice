package com.example.myspringbootpractice.MyExceptions;

public class UserRegisterException extends RuntimeException {

    public UserRegisterException(String message) {
        super(message);
    }

}
