package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.bean.Empresa;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.service.EmpresaService;

@SpringBootTest
public class EmpresaServiceTest {

    @MockBean
    private EmpresaRepository empresaRepository;

    private final EmpresaService empresaService = new EmpresaService(empresaRepository);

    @Test
    public void testCreateEmpresa() {
        Empresa newEmpresa = new Empresa("NombreEmpresa", "DescripcionEmpresa");
        empresaRepository.save(newEmpresa);
        Empresa savedEmpresa = empresaService.createEmpresa(newEmpresa);

        assertNotNull(savedEmpresa);
        assertEquals("NombreEmpresa", savedEmpresa.getNombre());
    }

    @Test
    public void testGetEmpresaById() {
        Long empresaId = 1L;
        Empresa empresa = new Empresa("NombreEmpresa", "DescripciónEmpresa");
        when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(empresa));

        // Act
        Optional<Empresa> foundEmpresa = empresaService.getEmpresaById(empresaId);

        // Assert
        assertTrue(foundEmpresa.isPresent());
        assertEquals("NombreEmpresa", foundEmpresa.get().getNombre());
        assertEquals("DescripciónEmpresa", foundEmpresa.get().getDescripcion());

        verify(empresaRepository, times(1)).findById(empresaId);
    }

    @Test
    public void testDeleteEmpresaById() {
        Long empresaId = 1L;

        empresaService.deleteEmpresaById(empresaId);

        verify(empresaRepository, times(1)).deleteById(empresaId);
    }
}
