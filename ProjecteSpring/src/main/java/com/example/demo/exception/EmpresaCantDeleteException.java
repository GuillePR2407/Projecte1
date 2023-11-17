	package com.example.demo.exception;

public class EmpresaCantDeleteException extends RuntimeException {

	public EmpresaCantDeleteException(Long id) {
		super("Could not delete Empresa " + id);
	}
}
