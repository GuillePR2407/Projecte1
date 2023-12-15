package com.example.demo.test;

import com.example.demo.bean.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // Opcional: usa perfiles para configuraciones específicas de prueba
@Transactional
public class LoadDatabaseTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private LoadDatabase loadDatabase;

    @Test
    public void testInitDatabase() throws Exception {
        // Ejecutar la lógica de inicialización de la base de datos
        loadDatabase.initDatabase(empresaRepository, ofertaRepository).run();

        // Realiza aserciones para verificar que las entidades se han guardado correctamente
        // Puedes utilizar los repositorios para consultar y verificar que las entidades se hayan guardado correctamente.
        List<Empresa> empresasConNombreExacto = empresaRepository.findByNom("Giyesl");
        List<Empresa> empresasConNombreSimilar = empresaRepository.findByNomLike("fransl");

        // Asegúrate de que las listas no sean nulas y contengan las empresas esperadas
        assertNotNull(empresasConNombreExacto);
        assertEquals(1, empresasConNombreExacto.size()); // Ajusta según la cantidad esperada de empresas con este nombre

        assertNotNull(empresasConNombreSimilar);
        // Ajusta según la cantidad esperada de empresas con nombres similares
        assertTrue(empresasConNombreSimilar.size() >= 2);

        // También puedes realizar aserciones específicas sobre las empresas recuperadas, si es necesario
    }
}
