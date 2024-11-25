package com.avanzadas.proyectoWEB.service;

import com.avanzadas.proyectoWEB.entity.Grabacion;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface GrabacionService {

	void guardarFrame(Long userId, byte[] imagenData);

	void guardarGrabaciones();
	ByteArrayInputStream buscarGrabacion(String id);
	List<Grabacion> listarGrabaciones();
}
