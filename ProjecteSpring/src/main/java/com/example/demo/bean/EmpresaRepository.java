package com.example.demo.bean;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	List<Empresa> findByNom(String nom);
	
	List<Empresa> findByNomLike(String Nom);
	
	
}

