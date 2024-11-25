package com.avanzadas.proyectoWEB.service;

import com.avanzadas.proyectoWEB.entity.Usuario;

import java.util.List;

public interface UsuarioService {
	void registrarUsuario(Usuario usuario);
	Usuario buscarUsuario(Long id);

}
