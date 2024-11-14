package com.avanzadas.proyectoWEB.service;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface GrabacionService {

	void guardarFrame(int userId, byte[] imagenData);

	void guardarVideos();
	ByteArrayInputStream buscarGrabacion(String id);
	List<String> listarGrabaciones();
}
