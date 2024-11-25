package com.avanzadas.proyectoWEB.repository;

import com.avanzadas.proyectoWEB.adaptadores.AdaptadorBaseDatos;
import com.avanzadas.proyectoWEB.entity.Usuario;
import com.avanzadas.proyectoWEB.fabricas.baseDeDatos.Persistencia;
import com.avanzadas.proyectoWEB.fabricas.baseDeDatos.FabricaBaseDeDatos;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	public List<Usuario> buscarTodos() {
		List<Usuario> usuarios = new ArrayList<>();
		try (Connection conn = (Connection) adaptadorBaseDatos.obtenerConexion();
				 PreparedStatement pstmt = conn.prepareStatement(
					 "SELECT * FROM Usuario");
		)
		{
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEmail(rs.getString("email"));
				usuarios.add(usuario);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public Usuario buscarPorId(Long id) {
		Usuario usuario = new Usuario();
		try (Connection conn = (Connection) adaptadorBaseDatos.obtenerConexion();
				 PreparedStatement pstmt = conn.prepareStatement(
					 "SELECT * FROM Usuario where id = ? ");
		)
		{
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()){
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setDireccionIp(rs.getString("direccionIp"));
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return usuario;



	}
}
