package com.avanzadas.proyectoWEB.service;

import com.avanzadas.proyectoWEB.repository.GrabacionRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.util.List;

@Service
public class GrabacionServiceImpl implements GrabacionService{

	GrabacionRepository grabacionRepository;
	public GrabacionServiceImpl(GrabacionRepository grabacionRepository){
		this.grabacionRepository = grabacionRepository;
	}

	@Override
	public void guardarFrame(int userId, byte[] imagenData){
		grabacionRepository.guardarFrame(userId, imagenData);
	}

	@Override
	public void guardarVideos() {

		for(int i =1;i<3;i++) {
			List<byte[]> frames = grabacionRepository.obtenerFramesTemporales(i);

			try {
				Path videoTemp = grabacionRepository.crearVideoTemporalConFrames(frames, 10);
				grabacionRepository.guardarVideo(videoTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		grabacionRepository.eliminarFramesTemporales();

	}

	@Override
	public ByteArrayInputStream buscarGrabacion(String id) {
		return grabacionRepository.buscarPorId(id);
	}

	@Override
	public List<String> listarGrabaciones() {
		return grabacionRepository.buscarTodos();
	}
}
