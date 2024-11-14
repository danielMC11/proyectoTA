package com.avanzadas.proyectoWEB.config;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.Properties;

@Component
public class PropiedadesBean {
	public static String obtenerPropiedad(String nombre){
		Properties prop = new Properties();

		try (FileInputStream input = new FileInputStream("./src/main/resources/application.properties")) {
			prop.load(input);
		} catch (Exception e) {
			System.err.println("ERROR AL CARGAR ARCHIVO: " + e.getMessage());
		}
		return prop.getProperty(nombre);
	}
}
