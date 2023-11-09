package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmpresaCantDeleteAdvice {
	@ResponseBody
	  @ExceptionHandler(EmpresaCantDeleteException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String empresaCantDeleteHandler(EmpresaCantDeleteException ex) {
	    return ex.getMessage();
	  }

}
