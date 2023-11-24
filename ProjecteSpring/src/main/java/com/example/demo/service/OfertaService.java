package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;
import com.example.demo.bean.Oferta;
import com.example.demo.bean.OfertaRepository;
import com.example.demo.exception.EmpresaNotFoundException;

import java.util.List;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;
    private final EmpresaRepository empresaRepository;

    @Autowired
    public OfertaService(OfertaRepository ofertaRepository, EmpresaRepository empresaRepository) {
        this.ofertaRepository = ofertaRepository;
        this.empresaRepository = empresaRepository;
    }

    public Oferta createOferta(Oferta newOferta) {
        Empresa empresa = empresaRepository.findById(newOferta.getEmpresa())
        		.orElseThrow(() -> new EmpresaNotFoundException(newOferta.getEmpresa()));

        newOferta.setEmpresa(empresa);
        return ofertaRepository.save(newOferta);
    }

    public List<Oferta> getAllOfertas() {
        return ofertaRepository.findAll();
    }

    // Otros métodos de servicio según sea necesario
}