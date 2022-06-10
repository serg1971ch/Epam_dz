package by.store.controllers;

import by.store.Constants;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private static final String DEFAULT_ERROR_VIEW = "errorInput";

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) {
        model.addAttribute(Constants.MESSAGE, e.getMessage());
        return DEFAULT_ERROR_VIEW;
    }
}