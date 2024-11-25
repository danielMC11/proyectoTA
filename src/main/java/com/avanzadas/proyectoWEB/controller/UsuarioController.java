package com.avanzadas.proyectoWEB.controller;

import com.avanzadas.proyectoWEB.entity.Usuario;
import com.avanzadas.proyectoWEB.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario/")
public class UsuarioController {
	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService){
		this.usuarioService = usuarioService;
	}
	@GetMapping("registro-form")
	public String paginaRegistrarUsuario(Model model){
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	@PostMapping("registrar")
	public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario){
			usuarioService.registrarUsuario(usuario);
			return "redirect:/usuario/confirmacion-registro";
	}

	@GetMapping("confirmacion-registro")
	public String usuarioRegistrado(){
		return "confirmacion";
	}


}
