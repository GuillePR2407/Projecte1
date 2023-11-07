package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;
import com.example.demo.bean.Oferta;
import com.example.demo.bean.OfertaRepository;
import com.example.demo.exception.OfertaNotFoundException;

@RestController
class OfertaController {

    private final OfertaRepository ofertaRepository;
    private final EmpresaRepository empresaRepository;

    OfertaController(OfertaRepository ofertaRepository, EmpresaRepository empresaRepository) {
        this.ofertaRepository = ofertaRepository;
        this.empresaRepository = empresaRepository;
    }

    @GetMapping("/ofertas")
    List<Oferta> all() {
        return ofertaRepository.findAll();
    }

    @PostMapping("/ofertas")
    Oferta newOferta(@RequestBody Oferta newOferta) {
        return ofertaRepository.save(newOferta);
    }

    @GetMapping("/empresas/{idEmpresa}/ofertas")
    List<Oferta> obtenerOfertasPorEmpresa(@PathVariable Long idEmpresa) {
        // Verificar si la empresa existe
        Empresa empresa = empresaRepository.findById(idEmpresa)
            .orElseThrow(() -> new OfertaNotFoundException(idEmpresa));
        
        // Obtener las ofertas pertenecientes a la empresa
        return ofertaRepository.findByEmpresa(empresa);
    }

    @PutMapping("/ofertas/{id}")
    Oferta replaceOferta(@RequestBody Oferta newOferta, @PathVariable Long id) {
        return ofertaRepository.findById(id).map(oferta -> {
            oferta.setNom(newOferta.getNom());
            oferta.setDescripcio(newOferta.getDescripcio());
            oferta.setStatus(newOferta.getStatus());
            oferta.setRegistDate(newOferta.getRegistDate());
            return ofertaRepository.save(oferta);
        }).orElseGet(() -> {
            newOferta.setId(id);
            return ofertaRepository.save(newOferta);
        });
    }

    @DeleteMapping("/ofertas/{id}")
    void deleteOferta(@PathVariable Long id) {
        ofertaRepository.deleteById(id);
    }
}
