package com.example.myspringbootpractice.myException;

import com.example.myspringbootpractice.hash.PasswordService;
import com.example.myspringbootpractice.myException.forgotPassword.InvalidException;
import com.example.myspringbootpractice.myException.registerExceptionExtend.AccountException;
import com.example.myspringbootpractice.myException.registerExceptionExtend.EmailException;
import com.example.myspringbootpractice.myException.registerExceptionExtend.FailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String,Object>> handleResponseStatusException(ResponseStatusException ex){
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", ex.getReason());
        errorResponse.put("errorCode", ex.getStatusCode());
        errorResponse.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }
    //註冊帳號已存在的異常
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<Map<String,Object>>  handleAccountException(AccountException ex){
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("errorCode", ex.getErrorCode());
        errorResponse.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(ex.getErrorCode()).body(errorResponse);
    }
    //註冊信箱已存在的異常
    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Map<String,Object>>  handlePasswordException(EmailException ex){
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("errorCode", ex.getErrorCode());
        errorResponse.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(ex.getErrorCode()).body(errorResponse);
    }
    //註冊失敗的異常
    @ExceptionHandler(FailException.class)
    public ResponseEntity<Map<String,Object>>  handleFailException(FailException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("errorCode", ex.getErrorCode());
        errorResponse.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(ex.getErrorCode()).body(errorResponse);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<Map<String,Object>>  handleInvalidException(FailException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("errorCode", ex.getErrorCode());
        errorResponse.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(ex.getErrorCode()).body(errorResponse);
    }
}
