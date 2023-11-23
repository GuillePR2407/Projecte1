package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    public void testSaveAndFindById() {
        // Crear una empresa para la prueba
        Empresa empresa = new Empresa("Empresa de prueba", "Descripción de la empresa de prueba");

        // Guardar la empresa en la base de datos
        empresaRepository.save(empresa);

        // Buscar la empresa por ID
        Empresa retrievedEmpresa = empresaRepository.findById(empresa.getId()).orElse(null);

        // Verificar que la empresa se haya guardado correctamente y se pueda recuperar
        assertNotNull(retrievedEmpresa);
        assertEquals("Empresa de prueba", retrievedEmpresa.getNombre());
        assertEquals("Descripción de la empresa de prueba", retrievedEmpresa.getDescripcion());
        // Agregar más verificaciones según sea necesario
    }

    // Agregar más pruebas según sea necesario
}
