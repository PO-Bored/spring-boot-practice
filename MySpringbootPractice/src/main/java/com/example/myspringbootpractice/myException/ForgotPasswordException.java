package com.example.myspringbootpractice.myException;

public class ForgotPasswordException extends RuntimeException {
    private final Integer errorCode;
    private final String errorMessage;

    public ForgotPasswordException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
