package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmpresaNotEmptyAdvice {
    @ResponseBody
    @ExceptionHandler(EmpresaNotEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String empresaNotEmptyHandler(EmpresaNotEmptyException ex) {
        return ex.getMessage();
    }
}
