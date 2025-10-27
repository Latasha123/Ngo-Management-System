package com.example.ngo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ngo.dtos.NgoDto;
import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.service.NGOService;

@RestController
@RequestMapping("/api/ngo")
public class NGOController {

	private final NGOService ngoService;

	public NGOController(NGOService ngoService) {
		this.ngoService = ngoService;
	}

	@GetMapping
	public ResponseDto getAllNGos() {
		return ngoService.getAllNGos();
	}

	@GetMapping("/{id}")
	public ResponseDto get(@PathVariable("id") String id) {
		return ngoService.getNGOById(id);
	}

	@PostMapping("/addNGO")
	public ResponseDto createNGO(@RequestBody NgoDto ngoDto) {
		return ngoService.createNGO(ngoDto);
	}

	@PutMapping("/updateNGO")
	public ResponseDto updateNGODetails(@PathVariable("id") String id, @RequestBody NgoDto ngo) {
		return ngoService.updateNGODetails(id, ngo);
	}

	@DeleteMapping("/{id}")
	public ResponseDto deleteNGO(@PathVariable("id") String id) {
		return ngoService.deleteNGO(id);
	}
}