package com.avanzadas.proyectoWEB.controller;

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
@RequestMapping("/admin/")
public class GrabacionController {

	GrabacionService grabacionService;
	public GrabacionController(GrabacionService grabacionService){
		this.grabacionService	= grabacionService;
	}



	@GetMapping("ver-video")
	public String mostrarVideo(Model model) {
		List<String> videosId = grabacionService.listarGrabaciones();

		System.err.println("IDS" + videosId);

		model.addAttribute("videosList", videosId);
		return "videos";
	}

	@GetMapping("/video/{id}")
	public ResponseEntity<InputStreamResource> obtenerVideo(@PathVariable String id){

		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "inline")
			.contentType(MediaType.parseMediaType("video/mp4"))
			.body(new InputStreamResource(grabacionService.buscarGrabacion(id)));

	}



}
