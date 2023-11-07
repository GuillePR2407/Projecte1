package com.example.demo.exception;

public class OfertaNotFoundException extends RuntimeException {

	public OfertaNotFoundException(Long id) {
		super("Could not find Oferta " + id);
	}
}
