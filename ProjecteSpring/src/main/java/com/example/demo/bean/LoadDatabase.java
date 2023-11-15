package com.example.demo.bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Calendar;

@Configuration
class LoadDatabase {
    Calendar fechaRegistro = Calendar.getInstance();
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmpresaRepository empresaRepository, OfertaRepository ofertaRepository) {
        Empresa empresa1 = new Empresa("Giyesl", "klklklklkl");
        Empresa empresa2 = new Empresa("fransl", "klklklklkl");

        // Guardar las empresas primero
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        // Use the saved empresas to create ofertas
        Oferta oferta1 = new Oferta("Activa", "programador", "Descripción ", fechaRegistro, empresa1);
        Oferta oferta2 = new Oferta("Activa", "sistemas", "Descripción ç", fechaRegistro, empresa1);
        Oferta oferta3 = new Oferta("Pendiente", "programador", "Descripción a", fechaRegistro, empresa2);

        // Save the ofertas
        ofertaRepository.save(oferta1);
        ofertaRepository.save(oferta2);
        ofertaRepository.save(oferta3);

        return args -> {
            log.info("Preloading " + empresa1);
            log.info("Preloading " + empresa2);
            log.info("Preloading " + oferta1);
            log.info("Preloading " + oferta2);
            log.info("Preloading " + oferta3);
        };
    }
}