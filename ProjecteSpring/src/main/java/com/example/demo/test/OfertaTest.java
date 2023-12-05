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
        
    	// Crear una empresa para asociar con la oferta
        Empresa empresa = new Empresa("Empresa1", "DescripciónEmpresa1");
        
        // Crear una instancia de Oferta
        Oferta oferta = new Oferta(Status.ACTIVA, "Oferta1", "Descripción1", Sector.DAM, Calendar.getInstance(), empresa);
        
        // Verificar que los valores se han establecido correctamente
        assertEquals(Status.ACTIVA, oferta.getStatus());
        assertEquals("Oferta1", oferta.getNom());
        assertEquals("Descripción1", oferta.getDescripcio());
        assertEquals(Sector.DAM, oferta.getSector());
    }

    @Test
    public void testGetEmpresa() {
    	
        // Crear una empresa para asociar con la oferta
        Empresa empresa = new Empresa("Empresa2", "DescripciónEmpresa2");
        
        // Crear una instancia de Oferta
        Oferta oferta = new Oferta(Status.ACTIVA, "Oferta1", "Descripción1", Sector.DAM, Calendar.getInstance(), empresa);
        
        // Verificar que el método getEmpresa devuelve el ID correcto de la empresa asociada
        assertEquals(empresa.getId(), oferta.getEmpresa());
    }
}
