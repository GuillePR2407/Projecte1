package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> consultarEmpresa(@PathVariable Long id) {
        // Implementa la lógica para consultar una empresa por su ID
    	 Optional<Empresa> empresa = empresaRepository.findById(id);
         if (empresa.isPresent()) {
             return ResponseEntity.ok(empresa.get());
         } else {
             return ResponseEntity.notFound().build();
         }
    }

    @PostMapping
    public ResponseEntity<Empresa> agregarEmpresa(@RequestBody Empresa empresa) {
        // Implementa la lógica para agregar una empresa
    	Empresa nuevaEmpresa = empresaRepository.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> modificarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        // Implementa la lógica para modificar una empresa
    	if (empresaRepository.existsById(id)) {
            empresa.setId(id);
            Empresa empresaModificada = empresaRepository.save(empresa);
            return ResponseEntity.ok(empresaModificada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpresa(@PathVariable Long id) {
        // Implementa la lógica para eliminar una empresa
    	if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
