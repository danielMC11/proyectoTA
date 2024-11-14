package com.avanzadas.proyectoWEB.adaptadores;

import java.sql.Connection;
import java.sql.SQLException;

public interface AdaptadorBaseDatos<T> {
    T obtenerConexion() throws Exception;
    void determinarParametrosConexion();
		String getDbname();
}
