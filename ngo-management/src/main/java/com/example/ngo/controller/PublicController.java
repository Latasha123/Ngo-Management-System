package com.example.ngo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.service.FundraiserService;
import com.example.ngo.service.NGOService;

@RestController
@RequestMapping("/api/public")
public class PublicController {
	private final NGOService ngoService;
	private final FundraiserService frService;

	public PublicController(NGOService ngoService, FundraiserService frService) {
		this.ngoService = ngoService;
		this.frService = frService;
	}

	@GetMapping("/ngos")
	public ResponseDto ngos() {
		return ngoService.getAllNGos();
	}

	@GetMapping("/fundraisers")
	public ResponseDto getAllFundraisers() {
		return frService.getAllFundraisers();
	}
}