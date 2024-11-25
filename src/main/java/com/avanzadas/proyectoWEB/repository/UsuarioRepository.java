package com.avanzadas.proyectoWEB.repository;

import com.avanzadas.proyectoWEB.entity.Usuario;

import java.util.List;

public interface UsuarioRepository {
	void guardar(Usuario obj);
	List<Usuario> buscarTodos();

	Usuario buscarPorId(Long id);
}
