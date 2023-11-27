package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;
import com.example.demo.bean.Oferta;
import com.example.demo.bean.Sector;
import com.example.demo.bean.OfertaRepository;
import com.example.demo.exception.EmpresaNotFoundException;
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
    
    @GetMapping("/ofertas/nom/{nom}")
    List<Oferta> obtenerOfertasPorNombre(@PathVariable String nom) {
        return ofertaRepository.findByNom(nom);
    }

    @GetMapping("/ofertas/sector/{sector}")
    List<Oferta> obtenerOfertasPorSector(@PathVariable Sector sector) {
        return ofertaRepository.findBySector(sector);
    }

    @PostMapping("/ofertas")
    Oferta newOferta(@RequestBody Oferta newOferta) {
        Empresa empresa = empresaRepository.findById(newOferta.getEmpresa())
                .orElseThrow(() -> new EmpresaNotFoundException(newOferta.getEmpresa()));

        newOferta.setEmpresa(empresa);
        return ofertaRepository.save(newOferta);
    }
    
    @GetMapping("/ofertas/{id}")
    Oferta one(@PathVariable Long id) {
        return ofertaRepository.findById(id).orElseThrow(() -> new OfertaNotFoundException(id));
    }
    

    @GetMapping("/empresas/{empresaId}/ofertas")
    List<Oferta> obtenerOfertasPorEmpresa(@PathVariable Long empresaId) {
        return ofertaRepository.findByEmpresaId(empresaId);
    }

    @PutMapping("/ofertas/{id}")
    Oferta replaceOferta(@RequestBody Oferta newOferta, @PathVariable Long id) {
        return ofertaRepository.findById(id).map(oferta -> {
            Empresa empresa = empresaRepository.findById(newOferta.getEmpresa())
                    .orElseThrow(() -> new EmpresaNotFoundException(newOferta.getEmpresa()));

            oferta.setNom(newOferta.getNom());
            oferta.setDescripcio(newOferta.getDescripcio());
            oferta.setStatus(newOferta.getStatus());
            oferta.setRegistDate(newOferta.getRegistDate());
            oferta.setEmpresa(empresa);
            return ofertaRepository.save(oferta);
        }).orElseGet(() -> {
            newOferta.setId(id);
            Empresa empresa = empresaRepository.findById(newOferta.getEmpresa())
                    .orElseThrow(() -> new EmpresaNotFoundException(newOferta.getEmpresa()));

            newOferta.setEmpresa(empresa);
            return ofertaRepository.save(newOferta);
        });
    }

    @DeleteMapping("/ofertas/{id}")
    void deleteOferta(@PathVariable Long id) {
        ofertaRepository.deleteById(id);
    }
}

