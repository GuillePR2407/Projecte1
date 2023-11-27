package com.example.demo.test;

import com.example.demo.bean.*;
import com.example.demo.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OfertaServiceTest {

    @Mock
    private OfertaRepository ofertaRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private OfertaService ofertaService;

    @Test
    public void testCreateOferta() {
        // Configurar el comportamiento esperado del repositorio de empresa
        Empresa empresa = new Empresa("Empresa de prueba", "Descripción de la empresa de prueba");
        when(empresaRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(empresa));

        // Configurar el comportamiento esperado del repositorio de oferta
        Oferta oferta = new Oferta(Status.ACTIVA, "Oferta de prueba", "Descripción de prueba", Sector.DAM, Calendar.getInstance(), empresa);
        when(ofertaRepository.save(Mockito.any(Oferta.class))).thenReturn(oferta);

        // Llamar al método del servicio
        Oferta createdOferta = ofertaService.createOferta(new Oferta());

        // Verificar que la oferta se haya creado correctamente
        assertEquals("Activa", createdOferta.getStatus());
        assertEquals("Oferta de prueba", createdOferta.getNom());
        // Agregar más verificaciones según sea necesario
    }

    @Test
    public void testGetAllOfertas() {
        Empresa empresa = new Empresa("Empresa de prueba", "Descripción de la empresa de prueba");
        // Configurar el comportamiento esperado del repositorio de oferta
        Oferta oferta1 = new Oferta(Status.ACTIVA, "Oferta 1", "Descripción 1", Sector.DAM, Calendar.getInstance(), empresa);
        Oferta oferta2 = new Oferta(Status.INACTIVA, "Oferta 2", "Descripción 2", Sector.DAW, Calendar.getInstance(), empresa);
        when(ofertaRepository.findAll()).thenReturn(Arrays.asList(oferta1, oferta2));

        // Llamar al método del servicio
        List<Oferta> allOfertas = ofertaService.getAllOfertas();

        // Verificar que se hayan recuperado correctamente las ofertas
        assertEquals(2, allOfertas.size());
        // Agregar más verificaciones según sea necesario
    }

    // Agregar más pruebas según sea necesario
}
