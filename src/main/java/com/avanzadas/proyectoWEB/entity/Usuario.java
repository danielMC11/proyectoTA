package com.avanzadas.proyectoWEB.entity;



public class Usuario {
		private Long id;
		private String nombre;
		private String apellido;
		private String email;
		private String direccionIp;

		public Usuario() {
		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDireccionIp() {
			return direccionIp;
		}

		public void setDireccionIp(String direccionIp) {
			this.direccionIp = direccionIp;
		}
}
