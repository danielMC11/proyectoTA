package com.avanzadas.proyectoWEB.entity;

public class Grabacion {
	private Long id;
	private byte[] video;
	private Usuario usuario;

	public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public byte[] getVideo() {
			return video;
		}

		public void setVideo(byte[] video) {
			this.video = video;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
}
