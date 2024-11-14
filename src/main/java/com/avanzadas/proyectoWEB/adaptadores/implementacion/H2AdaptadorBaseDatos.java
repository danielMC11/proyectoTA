package com.avanzadas.proyectoWEB.adaptadores.implementacion;

import com.avanzadas.proyectoWEB.adaptadores.AdaptadorBaseDatos;


import com.avanzadas.proyectoWEB.config.PropiedadesBean;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2AdaptadorBaseDatos implements AdaptadorBaseDatos<Connection> {
    private static H2AdaptadorBaseDatos instanciaAdaptadorH2;
    private String url;
		private String dbname;
		private String user;
		private String password;

		private H2AdaptadorBaseDatos(){
			crearTablas();
    }

    public static H2AdaptadorBaseDatos obtenerInstancia() {
        if (instanciaAdaptadorH2 == null) {
            instanciaAdaptadorH2 = new H2AdaptadorBaseDatos();
        }
        return instanciaAdaptadorH2;
    }

    @Override
    public Connection obtenerConexion() throws Exception {
			determinarParametrosConexion();
        return DriverManager.getConnection(
					url + "/" + dbname + ";" + "USER=" + user + ";PASSWORD=" + password
        );
    }

	@Override
	public String getDbname() {
		return dbname;
	}

	@Override
    public void determinarParametrosConexion(){
			 url = PropiedadesBean.obtenerPropiedad("h2.url");
			 dbname =  PropiedadesBean.obtenerPropiedad("h2.dbname");
			 user =  PropiedadesBean.obtenerPropiedad("h2.username");
			 password =  PropiedadesBean.obtenerPropiedad("h2.password");
    }


    public void crearTablas(){
        try(
                Connection conn = obtenerConexion();
                Statement stmt = conn.createStatement()
        ){

            stmt.execute("CREATE TABLE IF NOT EXISTS USUARIO (" +
							"    id INT AUTO_INCREMENT PRIMARY KEY," +
							"    nombre VARCHAR(255) NOT NULL," +
							"    apellido VARCHAR(255) NOT NULL," +
							"    email VARCHAR(255) NOT NULL UNIQUE," +
							"    direccionIp VARCHAR(45) NOT NULL);");

        } catch (Exception e){
            System.err.println("ERROR AL CREAR TABLAS: " + e.getMessage());
        }
    }

}
