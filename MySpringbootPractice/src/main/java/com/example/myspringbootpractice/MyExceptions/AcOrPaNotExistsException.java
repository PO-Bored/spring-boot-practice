package com.example.myspringbootpractice.MyExceptions;

public class AcOrPaNotExistsException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public AcOrPaNotExistsException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
