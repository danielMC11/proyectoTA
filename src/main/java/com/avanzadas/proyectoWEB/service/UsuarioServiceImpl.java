package com.avanzadas.proyectoWEB.service;

import com.avanzadas.proyectoWEB.entity.Usuario;
import com.avanzadas.proyectoWEB.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioRepository usuarioRepository;


	public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		usuarioRepository.guardar(usuario);
	}
}