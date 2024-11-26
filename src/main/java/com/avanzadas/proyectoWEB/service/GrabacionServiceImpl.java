package com.avanzadas.proyectoWEB.service;

import com.avanzadas.proyectoWEB.entity.Grabacion;
import com.avanzadas.proyectoWEB.entity.Usuario;
import com.avanzadas.proyectoWEB.repository.GrabacionRepository;

import com.avanzadas.proyectoWEB.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.util.List;

@Service
public class GrabacionServiceImpl implements GrabacionService{

	GrabacionRepository grabacionRepository;
	UsuarioRepository usuarioRepository;
	public GrabacionServiceImpl(GrabacionRepository grabacionRepository, UsuarioRepository usuarioRepository){
		this.grabacionRepository = grabacionRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public void guardarFrame(Long userId, byte[] imagenData){
		grabacionRepository.guardarFrame(userId, imagenData);
	}

	@Override
	public void guardarGrabaciones() {

		System.err.println("GUARDANDO.....");

		for(Usuario usuario : usuarioRepository.buscarTodos()) {

			List<byte[]> frames = grabacionRepository.obtenerFramesTemporales(usuario.getId());

			if (!frames.isEmpty()){
			try {
				Path videoTemp = grabacionRepository.crearVideoTemporalConFrames(frames, 10);
				grabacionRepository.guardarGrabacion(videoTemp, usuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}

		}

		grabacionRepository.eliminarFramesTemporales();

	}

	@Override
	public ByteArrayInputStream buscarGrabacion(String id) {
		return grabacionRepository.buscarPorId(id);
	}

	@Override
	public List<Grabacion> listarGrabaciones() {
		return grabacionRepository.buscarTodos();
	}
}
