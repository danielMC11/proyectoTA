package com.avanzadas.proyectoWEB.controller;

import com.avanzadas.proyectoWEB.service.GrabacionService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grabacion/")
public class GrabacionRestController {

	GrabacionService grabacionService;
	public GrabacionRestController(GrabacionService grabacionService){
		this.grabacionService	= grabacionService;
	}

	@PostMapping("guardar-frame/{userId}")
	public ResponseEntity<Void> guardarFrame(@PathVariable Long userId, @RequestBody byte[] imagenData){
		grabacionService.guardarFrame(userId, imagenData);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("guardar-todos")
	public ResponseEntity<Void> guardarGrabaciones(){
		grabacionService.guardarGrabaciones();
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("ver/{id}")
	public ResponseEntity<InputStreamResource> obtenerGrabacion(@PathVariable String id){

		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "inline")
			.contentType(MediaType.parseMediaType("video/mp4"))
			.body(new InputStreamResource(grabacionService.buscarGrabacion(id)));

	}
}
