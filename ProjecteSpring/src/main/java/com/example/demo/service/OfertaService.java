package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.bean.Empresa;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.bean.Oferta;
import com.example.demo.repository.OfertaRepository;
import com.example.demo.exception.EmpresaNotFoundException;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;
    private final EmpresaRepository empresaRepository;

    public OfertaService(OfertaRepository ofertaRepository, EmpresaRepository empresaRepository) {
        this.ofertaRepository = ofertaRepository;
        this.empresaRepository = empresaRepository;
    }

    public Oferta createOferta(Oferta newOferta) {
        Empresa empresa = empresaRepository.findById(newOferta.getEmpresa())
        		.orElseThrow(() -> new EmpresaNotFoundException(newOferta.getEmpresa()));

        empresa.addOferta(newOferta);
        newOferta.setEmpresa(empresa);
        return ofertaRepository.save(newOferta);
    }

    public List<Oferta> getAllOfertas() {
        return ofertaRepository.findAll();
    }
}