package com.example.myspringbootpractice.myException;

public class ProductException extends RuntimeException {
    private final Integer errorCode;
    private final String errorMsg;

    public ProductException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
