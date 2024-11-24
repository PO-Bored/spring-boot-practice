package com.example.myspringbootpractice.myException.registerExceptionExtend;

import com.example.myspringbootpractice.myException.RegisterException;

public class FailException extends RegisterException {
    public FailException() {
        super(500, "系統異常，註冊失敗");
    }
}
