package com.avanzadas.proyectoWEB.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.nio.file.Path;


public interface GrabacionRepository {
		void guardarFrame(int userId, byte[] imagenData);
		public List<byte[]> obtenerFramesTemporales(int userId);
		Path crearVideoTemporalConFrames(List<byte[]> frames, int frameRate) throws IOException;
		void guardarVideo(Path rutaVideo);
		void eliminarFramesTemporales();
		List<String>  buscarTodos();
		ByteArrayInputStream buscarPorId(String id);
}
