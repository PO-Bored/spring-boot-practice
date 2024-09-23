package com.example.myspringbootpractice.MyExceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private int statusCode;
    private String message;
    public ErrorResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
