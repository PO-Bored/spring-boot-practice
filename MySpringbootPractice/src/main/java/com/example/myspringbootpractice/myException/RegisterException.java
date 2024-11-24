package com.example.myspringbootpractice.myException;

public class RegisterException extends RuntimeException {
    private final int errorCode;
    private final String errorMsg;

    public RegisterException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

