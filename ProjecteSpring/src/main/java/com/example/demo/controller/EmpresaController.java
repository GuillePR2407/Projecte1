package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    }

    @PostMapping
    public ResponseEntity<Empresa> agregarEmpresa(@RequestBody Empresa empresa) {
        // Implementa la lógica para agregar una empresa
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> modificarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        // Implementa la lógica para modificar una empresa
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpresa(@PathVariable Long id) {
        // Implementa la lógica para eliminar una empresa
    }
}
