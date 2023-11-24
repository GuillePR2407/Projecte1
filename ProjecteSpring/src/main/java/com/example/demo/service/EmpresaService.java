package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;

import java.util.List;
import java.util.Optional;

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

    public Empresa getEmpresaById(Long empresaId) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(empresaId);
        return optionalEmpresa.orElse(null);
    }

	public void deleteEmpresaById(Long empresaId) {
		empresaRepository.deleteById(empresaId);
	}

    // Otros métodos de servicio según sea necesario
}

