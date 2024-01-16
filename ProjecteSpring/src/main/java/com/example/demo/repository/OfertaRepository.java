package com.example.demo.repository;

import java.util.List;

import com.example.demo.bean.Oferta;
import com.example.demo.bean.Sector;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    List<Oferta> findByEmpresaId(Long EmpresaId);
    List<Oferta> findByNomLike(String Nom);
    List<Oferta> findBySector(Sector Sector);
}
