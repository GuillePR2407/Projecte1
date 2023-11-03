package com.example.demo.exception;

public class EmpresaNotFoundException extends RuntimeException {

	public EmpresaNotFoundException(Long id) {
		super("Could not find employee " + id);
	}
}
