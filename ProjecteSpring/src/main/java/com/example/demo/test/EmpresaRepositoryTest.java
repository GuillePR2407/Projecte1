package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.demo.bean.Empresa;
import com.example.demo.repository.EmpresaRepository;

@DataJpaTest
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    @DirtiesContext
    public void testGuardarYRecuperarEmpresa() {
        // Crear una nueva empresa
        Empresa empresa = new Empresa("NombreEmpresa", "DescripciónEmpresa");

        // Guardar la empresa en la base de datos
        empresa = empresaRepository.save(empresa);

        // Recuperar la empresa por ID
        Long empresaId = empresa.getId();
        Empresa empresaRecuperada = empresaRepository.findById(empresaId).orElse(null);

        // Verificar que la empresa recuperada no es nula
        assertNotNull(empresaRecuperada);

        // Verificar que los campos coinciden
        assertEquals("NombreEmpresa", empresaRecuperada.getNombre());
        assertEquals("DescripciónEmpresa", empresaRecuperada.getDescripcion());
    }

    @Test
    @DirtiesContext
    public void testObtenerTodasLasEmpresas() {
        // Crear empresas de prueba
        Empresa empresa1 = new Empresa("Empresa1", "Descripción1");
        Empresa empresa2 = new Empresa("Empresa2", "Descripción2");

        // Guardar las empresas en la base de datos
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        // Obtener todas las empresas
        List<Empresa> empresas = empresaRepository.findAll();

        // Verificar que se recuperaron las dos empresas
        assertEquals(2, empresas.size());
    }
}
