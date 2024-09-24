package com.example.myspringbootpractice.MyExceptions;

public class AccountExistsException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public AccountExistsException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getErroCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
