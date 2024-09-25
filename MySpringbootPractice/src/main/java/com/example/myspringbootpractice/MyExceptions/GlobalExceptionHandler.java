package com.example.myspringbootpractice.MyExceptions;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailExistsException.class)
    public String handleException(EmailExistsException e, Model model) {
        model.addAttribute("message", e.getErrorMessage());
        return "register";
    }

    @ExceptionHandler(AccountExistsException.class)
    public String handleException(AccountExistsException e, Model model) {
        model.addAttribute("message", e.getErrorMessage());
        return "register";
    }

    @ExceptionHandler(AcOrPaNotExistsException.class)
    public String handleException(AcOrPaNotExistsException e, Model model) {
        model.addAttribute("message", e.getErrorMessage());
        return "login";
    }
}
