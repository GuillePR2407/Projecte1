package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.EmpresaRepository;
import com.example.demo.service.EmpresaService;

@SpringBootTest
public class EmpresaServiceTest {

    @MockBean
    private EmpresaRepository empresaRepository;

    // Inject the EmpresaService with the mock repository
    private final EmpresaService empresaService = new EmpresaService(empresaRepository);

    @Test
    @DirtiesContext
    public void testCreateEmpresa() {
        // Arrange
        Empresa newEmpresa = new Empresa("NombreEmpresa", "DescripciónEmpresa");
        when(empresaRepository.save(newEmpresa)).thenReturn(newEmpresa);

        // Act
        Empresa savedEmpresa = empresaService.createEmpresa(newEmpresa);

        // Assert
        assertNotNull(savedEmpresa);
        assertEquals("NombreEmpresa", savedEmpresa.getNombre());
        assertEquals("DescripciónEmpresa", savedEmpresa.getDescripcion());

        // Verify that the save method was called once
        verify(empresaRepository, times(1)).save(newEmpresa);
    }

    @Test
    @DirtiesContext
    public void testGetAllEmpresas() {
        // Arrange
        List<Empresa> empresas = new ArrayList<>();
        empresas.add(new Empresa("Empresa1", "Descripción1"));
        empresas.add(new Empresa("Empresa2", "Descripción2"));
        when(empresaRepository.findAll()).thenReturn(empresas);

        // Act
        List<Empresa> allEmpresas = empresaService.getAllEmpresas();

        // Assert
        assertNotNull(allEmpresas);
        assertEquals(2, allEmpresas.size());

        // Verify that the findAll method was called once
        verify(empresaRepository, times(1)).findAll();
    }

    @Test
    @DirtiesContext
    public void testGetEmpresaById() {
        // Arrange
        Long empresaId = 1L;
        Empresa empresa = new Empresa("NombreEmpresa", "DescripciónEmpresa");
        when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(empresa));

        // Act
        Empresa foundEmpresa = empresaService.getEmpresaById(empresaId);

        // Assert
        assertNotNull(foundEmpresa);
        assertEquals("NombreEmpresa", foundEmpresa.getNombre());
        assertEquals("DescripciónEmpresa", foundEmpresa.getDescripcion());

        // Verify that the findById method was called once
        verify(empresaRepository, times(1)).findById(empresaId);
    }

    @Test
    @DirtiesContext
    public void testDeleteEmpresaById() {
        // Arrange
        Long empresaId = 1L;

        // Act
        empresaService.deleteEmpresaById(empresaId);

        // Verify that the deleteById method was called once
        verify(empresaRepository, times(1)).deleteById(empresaId);
    }
}
