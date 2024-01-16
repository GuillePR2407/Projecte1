package com.example.demo.repository;

import java.util.List;

import com.example.demo.bean.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	List<Empresa> findByNom(String nom);
	
	List<Empresa> findByNomLike(String Nom);
	
	
}

