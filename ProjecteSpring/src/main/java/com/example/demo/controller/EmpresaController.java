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
import com.example.demo.bean.EmpresaRepository;
import com.example.demo.exception.EmpresaNotFoundException;

@RestController
class EmpresaController {

	private final EmpresaRepository repository;

	EmpresaController(EmpresaRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/empresas")
	List<Empresa> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/empresas")
	Empresa newEmpresa(@RequestBody Empresa newEmpresa) {
		return repository.save(newEmpresa);
	}

	// Single item

	@GetMapping("/empresas/{id}")
	Empresa one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
	}

	@PutMapping("/empresas/{id}")
	Empresa replaceEmpresa(@RequestBody Empresa newEmpresa, @PathVariable Long id) {

		return repository.findById(id).map(empresa -> {
			empresa.setNombre(newEmpresa.getNombre());
			empresa.setDescripcion(newEmpresa.getDescripcion());
			return repository.save(empresa);
		}).orElseGet(() -> {
			newEmpresa.setId(id);
			return repository.save(newEmpresa);
		});
	}

	@DeleteMapping("/empresas/{id}")
	void deleteEmpresa(@PathVariable Long id) {
		repository.deleteById(id);
	}
}