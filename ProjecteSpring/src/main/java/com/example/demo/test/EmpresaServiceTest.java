package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;
import com.example.demo.service.EmpresaService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    public void testCreateEmpresa() {
        // Configurar el comportamiento esperado del repositorio de empresa
        Empresa empresa = new Empresa("Empresa de prueba", "Descripción de la empresa de prueba");
        when(empresaRepository.save(Mockito.any(Empresa.class))).thenReturn(empresa);

        // Llamar al método del servicio
        Empresa createdEmpresa = empresaService.createEmpresa(new Empresa());

        // Verificar que la empresa se haya creado correctamente
        assertEquals("Empresa de prueba", createdEmpresa.getNombre());
        assertEquals("Descripción de la empresa de prueba", createdEmpresa.getDescripcion());
        // Agregar más verificaciones según sea necesario
    }

    @Test
    public void testGetAllEmpresas() {
        // Configurar el comportamiento esperado del repositorio de empresa
        Empresa empresa1 = new Empresa("Empresa 1", "Descripción 1");
        Empresa empresa2 = new Empresa("Empresa 2", "Descripción 2");
        when(empresaRepository.findAll()).thenReturn(Arrays.asList(empresa1, empresa2));

        // Llamar al método del servicio
        List<Empresa> allEmpresas = empresaService.getAllEmpresas();

        // Verificar que se hayan recuperado correctamente las empresas
        assertEquals(2, allEmpresas.size());
        // Agregar más verificaciones según sea necesario
    }

    // Agregar más pruebas según sea necesario
}
