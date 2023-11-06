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
  
  @GetMapping("/ofertas/{id}")
  Oferta one(@RequestBody Oferta newOferta, @PathVariable Long id) {
    
    return repository.findById( newOferta.getEmpresa().getId())
      .orElseThrow(() -> new EmpresaNotFoundException(newOferta.getEmpresa().getId()));
  }

  @PutMapping("/empresas/{id}")
  Empresa replaceEmpresa(@RequestBody Empresa newEmpresa, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(empresa -> {
        empresa.setNombre(newEmpresa.getNombre());
        empresa.setDescripcion(newEmpresa.getDescripcion());
        return repository.save(empresa);
      })
      .orElseGet(() -> {
        newEmpresa.setId(id);
        return repository.save(newEmpresa);
      });
  }

  @DeleteMapping("/ofertas/{id}")
  void deleteOferta(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
