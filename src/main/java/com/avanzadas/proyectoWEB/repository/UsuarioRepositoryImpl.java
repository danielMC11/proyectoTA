package com.avanzadas.proyectoWEB.repository;

import com.avanzadas.proyectoWEB.adaptadores.AdaptadorBaseDatos;
import com.avanzadas.proyectoWEB.entity.Usuario;
import com.avanzadas.proyectoWEB.fabricas.baseDeDatos.Persistencia;
import com.avanzadas.proyectoWEB.fabricas.baseDeDatos.FabricaBaseDeDatos;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{

	private final AdaptadorBaseDatos adaptadorBaseDatos;
	public UsuarioRepositoryImpl(){
			adaptadorBaseDatos = FabricaBaseDeDatos.obtenerInstancia().obtenerAdaptador(Persistencia.H2);
	}

	@Override
	public void guardar(Usuario usuario) {

		try (Connection conn = (Connection) adaptadorBaseDatos.obtenerConexion();
				 PreparedStatement pstmt = conn.prepareStatement(
					 "INSERT INTO Usuario (nombre, apellido, email, direccionIp) VALUES (?, ?, ?, ?)");
		)
		{
			pstmt.setString(1, usuario.getNombre());
			pstmt.setString(2, usuario.getApellido());
			pstmt.setString(3, usuario.getEmail());
			pstmt.setString(4,usuario.getDireccionIp());

			// Ejecutar la inserción
			pstmt.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		return null;
	}

	@Override
	public List<Usuario> buscarTodos() {
		return null;
	}

	@Override
	public void eliminarPorId(Integer id) {

	}
}
