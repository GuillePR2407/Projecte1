package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;
import com.example.demo.bean.Oferta;
import com.example.demo.bean.OfertaRepository;
import com.example.demo.exception.EmpresaNotFoundException;
import com.example.demo.exception.EmpresaCantDeleteException;

@RestController
class EmpresaController {

    private final EmpresaRepository empresaRepository;
    private final OfertaRepository ofertaRepository;

    EmpresaController(EmpresaRepository empresaRepository, OfertaRepository ofertaRepository) {
        this.empresaRepository = empresaRepository;
        this.ofertaRepository = ofertaRepository;
    }

    @GetMapping("/empresas")
    List<Empresa> all() {
        return empresaRepository.findAll();
    }
    
    @GetMapping("/empresas/nom/{nom}")
    List<Empresa> obtenerEmpresasPorNom(@PathVariable String nom) {
        return empresaRepository.findByNomLike("%"+nom+"%");
    }

    @PostMapping("/empresas")
    Empresa newEmpresa(@RequestBody Empresa newEmpresa) {
        return empresaRepository.save(newEmpresa);
    }

    @GetMapping("/empresas/{id}")
    Empresa one(@PathVariable Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
    }

    @PutMapping("/empresas/{id}")
    Empresa replaceEmpresa(@RequestBody Empresa newEmpresa, @PathVariable Long id) {
        return empresaRepository.findById(id).map(empresa -> {
            empresa.setNombre(newEmpresa.getNombre());
            empresa.setDescripcion(newEmpresa.getDescripcion());
            return empresaRepository.save(empresa);
        }).orElseGet(() -> {
            newEmpresa.setId(id);
            return empresaRepository.save(newEmpresa);
        });
    }

    @DeleteMapping("/empresas/{id}")
    void deleteEmpresa(@PathVariable Long id) {
       try {
            if (!empresaTieneOferta(id)) {
                empresaRepository.deleteById(id);
            }
        } catch (Exception e) {   	
            throw new EmpresaCantDeleteException(id);
        }
    }

    public Boolean empresaTieneOferta(Long empresaId) {
        List<Oferta> ofertas = ofertaRepository.findByEmpresaId(empresaId);
        return !ofertas.isEmpty();
    }
}
