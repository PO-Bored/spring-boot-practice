package com.example.myspringbootpractice.myException.forgotPassword;

import com.example.myspringbootpractice.myException.ForgotPasswordException;

public class InvalidException extends ForgotPasswordException {

    public InvalidException() {
        super(404, "無效的信箱");
    }
}
