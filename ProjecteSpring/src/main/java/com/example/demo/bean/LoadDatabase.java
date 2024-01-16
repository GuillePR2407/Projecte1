package com.example.demo.bean;

import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.OfertaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Calendar;

@Configuration
public class LoadDatabase {
    Calendar fechaRegistro = Calendar.getInstance();
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(EmpresaRepository empresaRepository, OfertaRepository ofertaRepository) {
        Empresa empresa1 = new Empresa("Giyesl", "klklklklkl");
        Empresa empresa2 = new Empresa("fransl", "klklklklkl");
        Empresa empresa3 = new Empresa("fransl", "tonto");

        // Guardar las empresas primero
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);
        empresaRepository.save(empresa3);

        // Use the saved empresas to create ofertas
        Oferta oferta1 = new Oferta(Status.ACTIVA, "programador", "Descripción ", Sector.DAM, fechaRegistro, empresa1);
        Oferta oferta2 = new Oferta(Status.ACTIVA, "sistemas", "Descripción ç", Sector.ASIX, fechaRegistro, empresa1);
        Oferta oferta3 = new Oferta(Status.PENDIENTE, "programador", "Descripción a", Sector.DAW, fechaRegistro, empresa2);
        Oferta oferta4 = new Oferta(Status.PENDIENTE, "programador", "Descripción a", Sector.DAW, fechaRegistro, empresa3);


        // Save the ofertas
        ofertaRepository.save(oferta1);
        ofertaRepository.save(oferta2);
        ofertaRepository.save(oferta3);
        ofertaRepository.save(oferta4);

        return args -> {
            log.info("Preloading " + empresa1);
            log.info("Preloading " + empresa2);
            log.info("Preloading " + empresa3);
            log.info("Preloading " + oferta1);
            log.info("Preloading " + oferta2);
            log.info("Preloading " + oferta3);
            log.info("Preloading " + oferta4);
        };
    }
}