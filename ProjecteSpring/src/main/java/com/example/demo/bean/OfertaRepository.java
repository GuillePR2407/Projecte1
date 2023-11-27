package com.example.demo.bean;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    List<Oferta> findByEmpresaId(Long EmpresaId);
    List<Oferta> findByNom(String Nom);
    List<Oferta> findBySector(String Sector);
}
