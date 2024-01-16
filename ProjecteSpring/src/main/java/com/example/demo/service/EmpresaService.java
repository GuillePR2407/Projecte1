package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.bean.Empresa;
import com.example.demo.repository.EmpresaRepository;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

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