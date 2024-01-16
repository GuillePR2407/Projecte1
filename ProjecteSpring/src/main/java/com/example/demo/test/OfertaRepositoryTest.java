package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.OfertaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.demo.bean.*;

@DataJpaTest
public class OfertaRepositoryTest {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    @DirtiesContext
    public void testFindByEmpresaId() {
        // Crear una empresa para asociarla a las ofertas
        Empresa empresa = new Empresa("NombreEmpresa", "DescripciónEmpresa");
        empresa = empresaRepository.save(empresa);

        // Crear ofertas asociadas a la empresa
        Oferta oferta1 = new Oferta(Status.ACTIVA, "Oferta1", "Descripción1", Sector.DAM, Calendar.getInstance(), empresa);
        Oferta oferta2 = new Oferta(Status.INACTIVA, "Oferta2", "Descripción2", Sector.DAW, Calendar.getInstance(), empresa);
        ofertaRepository.save(oferta1);
        ofertaRepository.save(oferta2);

        // Obtener ofertas por ID de empresa
        Long empresaId = empresa.getId();
        List<Oferta> ofertasPorEmpresaId = ofertaRepository.findByEmpresaId(empresaId);

        // Verificar que se hayan recuperado las ofertas correctamente
        assertNotNull(ofertasPorEmpresaId);
        assertEquals(2, ofertasPorEmpresaId.size());
    }
}
