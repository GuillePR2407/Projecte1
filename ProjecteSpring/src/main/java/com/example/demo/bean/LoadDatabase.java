package com.example.demo.bean;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
	Calendar fechaRegistro = Calendar.getInstance();
	Empresa empresa1 = new Empresa("Giyesl", "klklklklkl");
	Empresa empresa2 = new Empresa("fransl", "klklklklkl");
	Oferta oferta1 = new Oferta("Activa", "programador", "Descripción de la oferta", fechaRegistro, empresa2);
	Oferta oferta2 = new Oferta("Activa", "sistemas", "Descripción de la oferta", fechaRegistro, empresa1);
	Oferta oferta3 = new Oferta("Pendiente", "programador", "Descripción de la oferta", fechaRegistro, empresa1);
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabaseEmpresa(EmpresaRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(empresa1));
			log.info("Preloading " + repository.save(empresa2));
		};
	}
	@Bean
	CommandLineRunner initDatabaseOferta(OfertaRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(oferta1));
			log.info("Preloading " + repository.save(oferta2));
			log.info("Preloading " + repository.save(oferta3));
		};
	}
}