package com.avanzadas.proyectoWEB.repository;

import com.avanzadas.proyectoWEB.entity.Grabacion;
import com.avanzadas.proyectoWEB.entity.Usuario;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.nio.file.Path;


public interface GrabacionRepository {
		void guardarFrame(Long userId, byte[] imagenData);
		public List<byte[]> obtenerFramesTemporales(Long userId);
		Path crearVideoTemporalConFrames(List<byte[]> frames, int frameRate) throws IOException;
		void guardarGrabacion(Path rutaVideo, Usuario usuario);
		void eliminarFramesTemporales();
		List<Grabacion>  buscarTodos();
		ByteArrayInputStream buscarPorId(String id);
}
