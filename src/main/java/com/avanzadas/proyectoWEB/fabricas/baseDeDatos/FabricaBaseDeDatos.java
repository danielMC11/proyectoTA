package com.avanzadas.proyectoWEB.fabricas.baseDeDatos;

import com.avanzadas.proyectoWEB.adaptadores.AdaptadorBaseDatos;
import com.avanzadas.proyectoWEB.adaptadores.implementacion.H2AdaptadorBaseDatos;
import com.avanzadas.proyectoWEB.adaptadores.implementacion.MongoAdaptadorBaseDatos;


public class FabricaBaseDeDatos {
	private static FabricaBaseDeDatos instanciaFabricaBaseDeDatos;

	private FabricaBaseDeDatos(){}

	public static FabricaBaseDeDatos obtenerInstancia(){
		if (instanciaFabricaBaseDeDatos == null) {
			instanciaFabricaBaseDeDatos = new FabricaBaseDeDatos();
		}
		return instanciaFabricaBaseDeDatos;
	}

	public AdaptadorBaseDatos obtenerAdaptador(Persistencia persistencia){
		return switch (persistencia){
			case H2 -> H2AdaptadorBaseDatos.obtenerInstancia();
			case MONGODB -> MongoAdaptadorBaseDatos.obtenerInstancia();
			default -> throw new IllegalArgumentException("Tipo de base de datos no soportado");
		};
	}
}
