package com.example.demo.test;

import org.junit.jupiter.api.Test;

import com.example.demo.bean.Empresa;
import com.example.demo.bean.Oferta;
import com.example.demo.bean.Sector;
import com.example.demo.bean.Status;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

public class EmpresaTest {

    @Test
    public void testAddOferta() {
        Empresa empresa = new Empresa("Empresa1", "Descripción1");
        Oferta oferta = new Oferta(Status.ACTIVA, "Oferta1", "Descripción1", Sector.DAM, Calendar.getInstance(), empresa);

        empresa.addOferta(oferta);

        assertTrue(empresa.getOfertas().contains(oferta));
        assertEquals(empresa.getId(), oferta.getEmpresa());
    }

    @Test
    public void testRemoveOferta() {
        Empresa empresa = new Empresa("Empresa2", "Descripción2");
        Oferta oferta = new Oferta(Status.ACTIVA, "Oferta2", "Descripción2", Sector.DAW, Calendar.getInstance(), empresa);
        empresa.addOferta(oferta);

        empresa.removeOferta(oferta);

        assertFalse(empresa.getOfertas().contains(oferta));
    }

    @Test
    public void testEqualsAndHashCode() {
        Empresa empresa1 = new Empresa("Empresa3", "Descripción3");
        Empresa empresa2 = new Empresa("Empresa3", "Descripción3");

        assertTrue(empresa1.equals(empresa2) && empresa2.equals(empresa1));
        assertEquals(empresa1.hashCode(), empresa2.hashCode());
    }
}