package com.avanzadas.proyectoWEB.entity;

import java.time.LocalDateTime;

public class Grabacion {
	private String grabacionId;
	private Usuario usuario;
	private LocalDateTime fechaYhora;

	public String getGrabacionId() {
		return grabacionId;
	}

	public void setGrabacionId(String grabacionId) {
		this.grabacionId = grabacionId;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public LocalDateTime getFechaYhora() {
		return fechaYhora;
	}

	public void setFechaYhora(LocalDateTime fechaYhora) {
		this.fechaYhora = fechaYhora;
	}
}
