package com.avanzadas.proyectoWEB;


import com.avanzadas.proyectoWEB.service.GrabacionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.avanzadas.proyectoWEB.service.GrabacionServiceImpl;

import com.avanzadas.proyectoWEB.repository.GrabacionRepositoryImpl;


@SpringBootApplication
public class ProyectoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoWebApplication.class, args);

		GrabacionService grabacionService = new GrabacionServiceImpl(new GrabacionRepositoryImpl());
		grabacionService.guardarVideos();

	}

}
