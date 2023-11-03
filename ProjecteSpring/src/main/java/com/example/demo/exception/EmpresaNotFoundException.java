package com.example.demo.bean;

public class EmpresaNotFoundException extends RuntimeException {

	public EmpresaNotFoundException(Long id) {
		super("Could not find employee " + id);
	}
}
