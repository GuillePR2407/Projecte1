package com.example.demo.test;

import com.example.demo.bean.*;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.OfertaRepository;
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
        Empresa empresa = new Empresa("Empresa1", "Descripción1");
        when(empresaRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(empresa));

        Oferta oferta = new Oferta(Status.ACTIVA, "Oferta de prueba", "Descripción de prueba", Sector.DAM, Calendar.getInstance(), empresa);
        when(ofertaRepository.save(Mockito.any(Oferta.class))).thenReturn(oferta);

        Oferta createdOferta = ofertaService.createOferta(new Oferta(Status.INACTIVA, "Oferta 2", "Descripción 2", Sector.DAW, Calendar.getInstance(), empresa));

        assertEquals(Status.INACTIVA, createdOferta.getStatus());
        assertEquals("Oferta 2", createdOferta.getNom());
    }

    @Test
    public void testGetAllOfertas() {
        Empresa empresa = new Empresa("Empresa1", "Descripción1");
        
        Oferta oferta1 = new Oferta(Status.ACTIVA, "Oferta 1", "Descripción 1", Sector.DAM, Calendar.getInstance(), empresa);
        Oferta oferta2 = new Oferta(Status.INACTIVA, "Oferta 2", "Descripción 2", Sector.DAW, Calendar.getInstance(), empresa);
        
        when(ofertaRepository.findAll()).thenReturn(Arrays.asList(oferta1, oferta2));

        List<Oferta> allOfertas = ofertaService.getAllOfertas();

        assertEquals(2, allOfertas.size());
        
    }
}