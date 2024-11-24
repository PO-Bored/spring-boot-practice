package com.example.myspringbootpractice.myException.registerExceptionExtend;

import com.example.myspringbootpractice.myException.RegisterException;

public class EmailException extends RegisterException{

    public EmailException() {
        super(401, "信箱已被使用");
    }
}
