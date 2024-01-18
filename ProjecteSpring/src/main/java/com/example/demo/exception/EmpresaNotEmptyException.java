package com.example.demo.exception;

public class EmpresaNotEmptyException extends RuntimeException {

    public EmpresaNotEmptyException(Long id) {
        super("Empresa " + id + " is not empty");
    }
}
