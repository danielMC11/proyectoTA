package com.avanzadas.proyectoWEB.repository;

import com.avanzadas.proyectoWEB.entity.Usuario;

import java.util.List;

public interface UsuarioRepository {
	void guardar(Usuario obj);
	Usuario buscarPorId(Integer id);
	List<Usuario> buscarTodos();
	void eliminarPorId(Integer id);
}
