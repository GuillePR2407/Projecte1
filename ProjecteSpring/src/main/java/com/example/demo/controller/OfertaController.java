package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.Oferta;
import com.example.demo.bean.EmpresaRepository;
import com.example.demo.bean.OfertaRepository;
import com.example.demo.exception.EmpresaNotFoundException;

@RestController
class OfertaController {

	private final OfertaRepository repository;

	OfertaController(OfertaRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/ofertas")
	List<Oferta> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/ofertas")
	Oferta newOferta(@RequestBody Oferta newOferta) {
		return repository.save(newOferta);
	}

	// Single item

	@GetMapping("/empresas/{id}/ofertas")
	Oferta empresesOfertas(@RequestBody Oferta newOferta, @PathVariable Long id) {

		return repository.findById(newOferta.getEmpresa().getId())
				.orElseThrow(() -> new EmpresaNotFoundException(newOferta.getEmpresa().getId()));
	}

	@PutMapping("/ofertas/{id}")
	Oferta replaceOferta(@RequestBody Oferta newOferta, @PathVariable Long id) {

		return repository.findById(id).map(oferta -> {
			oferta.setNom(newOferta.getNom());
			oferta.setDescripcio(newOferta.getDescripcio());
			oferta.setStatus(newOferta.getStatus());
			oferta.setRegistDate(newOferta.getRegistDate());
			return repository.save(oferta);
		}).orElseGet(() -> {
			newOferta.setId(id);
			return repository.save(newOferta);
		});
	}

	@DeleteMapping("/ofertas/{id}")
	void deleteOferta(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
