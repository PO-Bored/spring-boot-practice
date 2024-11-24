package com.example.myspringbootpractice.myException.registerExceptionExtend;

import com.example.myspringbootpractice.myException.RegisterException;

public class AccountException extends RegisterException {

    public AccountException() {
        super(401, "帳號已被使用");
    }


}
