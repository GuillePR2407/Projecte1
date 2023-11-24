package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa createEmpresa(Empresa newEmpresa) {
        return empresaRepository.save(newEmpresa);
    }

    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> getEmpresaById(Long empresaId) {
        return empresaRepository.findById(empresaId);
    }

    public void deleteEmpresaById(Long empresaId) {
        empresaRepository.deleteById(empresaId);
    }
}