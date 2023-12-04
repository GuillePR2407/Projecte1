package com.example.demo.test;

import org.junit.jupiter.api.Test;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.Oferta;
import com.example.demo.bean.Sector;
import com.example.demo.bean.Status;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class OfertaTest {

    @Test
    public void testGettersAndSetters() {
        // Crear una instancia de Oferta
        Oferta oferta = new Oferta();
        
        // Establecer valores
        oferta.setId(1L);
        oferta.setStatus(Status.ACTIVA);
        oferta.setNom("Oferta1");
        oferta.setDescripcio("Descripción1");
        oferta.setSector(Sector.DAM);
        Calendar fecha = Calendar.getInstance();
        oferta.setRegistDate(fecha);
        
        // Crear una empresa para asociar con la oferta
        Empresa empresa = new Empresa("Empresa1", "DescripciónEmpresa1");
        oferta.setEmpresa(empresa);
        
        // Verificar que los valores se han establecido correctamente
        assertEquals(1L, oferta.getId());
        assertEquals(Status.ACTIVA, oferta.getStatus());
        assertEquals("Oferta1", oferta.getNom());
        assertEquals("Descripción1", oferta.getDescripcio());
        assertEquals(Sector.DAM, oferta.getSector());
        assertEquals(fecha, oferta.getRegistDate());
        assertEquals(empresa, oferta.getEmpresa());
    }

    @Test
    public void testGetEmpresa() {
        // Crear una instancia de Oferta
        Oferta oferta = new Oferta();
        
        // Crear una empresa para asociar con la oferta
        Empresa empresa = new Empresa("Empresa2", "DescripciónEmpresa2");
        oferta.setEmpresa(empresa);
        
        // Verificar que el método getEmpresa devuelve el ID correcto de la empresa asociada
        assertEquals(empresa.getId(), oferta.getEmpresa());
    }
}
