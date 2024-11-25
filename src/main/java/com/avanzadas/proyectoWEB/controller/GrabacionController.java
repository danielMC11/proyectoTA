package com.avanzadas.proyectoWEB.controller;

import com.avanzadas.proyectoWEB.entity.Grabacion;
import com.avanzadas.proyectoWEB.service.GrabacionService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
@RequestMapping("/grabacion/")
public class GrabacionController {

	GrabacionService grabacionService;
	public GrabacionController(GrabacionService grabacionService){
		this.grabacionService	= grabacionService;
	}


	@GetMapping("ver-todos")
	public String listarGrabaciones(Model model) {
		List<Grabacion> grabaciones = grabacionService.listarGrabaciones();

		model.addAttribute("grabacionesList", grabaciones);
		return "grabaciones";
	}

}
