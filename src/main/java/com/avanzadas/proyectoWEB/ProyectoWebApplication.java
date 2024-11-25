package com.avanzadas.proyectoWEB;


import com.avanzadas.proyectoWEB.repository.UsuarioRepositoryImpl;
import com.avanzadas.proyectoWEB.service.GrabacionService;
import com.avanzadas.proyectoWEB.service.UsuarioServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProyectoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoWebApplication.class, args);

	}

}
