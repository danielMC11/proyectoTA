package com.avanzadas.proyectoWEB.adaptadores.implementacion;

import com.avanzadas.proyectoWEB.adaptadores.AdaptadorBaseDatos;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.avanzadas.proyectoWEB.config.PropiedadesBean;

import java.util.ArrayList;
import java.util.List;

public class MongoAdaptadorBaseDatos implements AdaptadorBaseDatos<MongoClient> {
	private static MongoAdaptadorBaseDatos instanciaAdaptadorMongo;

	private MongoAdaptadorBaseDatos() {
	}

	private String uri;
	private String dbname;

	public static MongoAdaptadorBaseDatos obtenerInstancia() {
		if (instanciaAdaptadorMongo == null) {
			instanciaAdaptadorMongo = new MongoAdaptadorBaseDatos();
		}
		return instanciaAdaptadorMongo;
	}

	@Override
	public MongoClient obtenerConexion() {
		determinarParametrosConexion();
		return MongoClients.create(uri);
	}

	@Override
	public void determinarParametrosConexion() {
		 uri = PropiedadesBean.obtenerPropiedad("mongo.uri"); // Ejemplo: "mongodb://localhost:27017"
		 dbname = PropiedadesBean.obtenerPropiedad("mongo.dbname");

	}

	public String getDbname() {
		return dbname;
	}

	public void crearColecciones() {
		try(MongoClient conn = obtenerConexion()){
			MongoDatabase db = conn.getDatabase(getDbname());
			List<String> colecciones = List.of("grabacion.files", "grabacion.chunks");  // Lista de nombres de colecciones

			for (String coleccion : colecciones) {
				if (!db.listCollectionNames().into(new ArrayList<>()).contains(coleccion))
						db.createCollection(coleccion);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
