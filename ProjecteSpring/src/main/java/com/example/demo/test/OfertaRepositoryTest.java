package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.Oferta;
import com.example.demo.bean.OfertaRepository;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class OfertaRepositoryTest {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Test
    public void testSaveAndFindById() {
        Empresa empresa = new Empresa();
		// Crear una oferta para la prueba
        Oferta oferta = new Oferta("Activa", "Oferta de prueba", "Descripción de prueba", Calendar.getInstance(), empresa);

        // Guardar la oferta en la base de datos
        ofertaRepository.save(oferta);

        // Buscar la oferta por ID
        Oferta retrievedOferta = ofertaRepository.findById(oferta.getId()).orElse(null);

        // Verificar que la oferta se haya guardado correctamente y se pueda recuperar
        assertNotNull(retrievedOferta);
        assertEquals("Activa", retrievedOferta.getStatus());
        assertEquals("Oferta de prueba", retrievedOferta.getNom());
        // Agregar más verificaciones según sea necesario
    }

    @Test
    public void testFindByEmpresaId() {
    	Empresa empresa = new Empresa();
		// Crear ofertas de prueba asociadas a una empresa específica
        Oferta oferta1 = new Oferta("Activa", "Oferta 1", "Descripción 1", Calendar.getInstance(), empresa);
        Oferta oferta2 = new Oferta("Inactiva", "Oferta 2", "Descripción 2", Calendar.getInstance(), empresa);
        ofertaRepository.saveAll(List.of(oferta1, oferta2));

        // Buscar ofertas por ID de empresa
        List<Oferta> ofertas = ofertaRepository.findByEmpresaId(empresa.getId());

        // Verificar que se hayan recuperado correctamente las ofertas asociadas a la empresa
        assertEquals(2, ofertas.size());
        // Agregar más verificaciones según sea necesario
    }

    // Agregar más pruebas según sea necesario
}
