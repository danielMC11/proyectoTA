package com.avanzadas.proyectoWEB.controller;

import com.avanzadas.proyectoWEB.service.GrabacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {

	GrabacionService grabacionService;
	public TestController(GrabacionService grabacionService){
		this.grabacionService	= grabacionService;
	}

	@PostMapping("guardarFrame/{userId}")
	public ResponseEntity<String> guardarFrame(@PathVariable int userId, @RequestBody byte[] imagenData){
		grabacionService.guardarFrame(userId, imagenData);
		return ResponseEntity.ok("Todo bien paisano");
	}

	@PostMapping("guardarVideos")
	public ResponseEntity<String> guardarcoso(){
		grabacionService.guardarVideos();
		return ResponseEntity.ok("AGUANTE DIOS");
	}
}
