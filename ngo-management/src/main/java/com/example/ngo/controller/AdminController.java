package com.example.ngo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ngo.dtos.FundraiserDto;
import com.example.ngo.dtos.NgoDto;
import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.service.DonationService;
import com.example.ngo.service.FundraiserService;
import com.example.ngo.service.NGOService;
import com.example.ngo.service.UserService;
import com.example.ngo.service.VolunteerApplicationService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private final NGOService ngoService;
	private final FundraiserService frService;
	private final DonationService donationService;
	private final VolunteerApplicationService volAppService;

	public AdminController(NGOService ngoService, FundraiserService frService, DonationService donationService,
			VolunteerApplicationService volAppService, UserService userService) {
		this.ngoService = ngoService;
		this.frService = frService;
		this.donationService = donationService;
		this.volAppService = volAppService;
	}

	@GetMapping("/ngos")
	public ResponseDto ngos() {
		return ngoService.getAllNGos();
	}

	@PostMapping("/ngos")
	public ResponseDto createNgo(@RequestBody NgoDto ngoDto) {
		return ngoService.createNGO(ngoDto);
	}

	@PutMapping("/ngos/{id}")
	public ResponseDto updateNgo(@PathVariable("id") String id, @RequestBody NgoDto ngo) {
		return ngoService.updateNGODetails(id, ngo);
	}

	@DeleteMapping("/ngos/{id}")
	public ResponseDto deleteNgo(@PathVariable("id") String id) {
		return ngoService.deleteNGO(id);
	}

	@GetMapping("/fundraisers")
	public ResponseDto getAllFundraisers() {
		return frService.getAllFundraisers();
	}

	@PostMapping("/fundraisers")
	public ResponseDto createFr(@RequestBody FundraiserDto f) {
		return frService.create(f);
	}

	@GetMapping("/donations")
	public ResponseDto donations() {
		return donationService.allDonations();
	}

	@PostMapping("/donations/{id}/approve")
	public ResponseDto approve(@PathVariable("id") String id, @RequestParam Boolean approved) {
		return donationService.approve(id, approved);
	}

	@GetMapping("/volapps")
	public ResponseDto volApps(@RequestParam String ngoId) {
		return volAppService.byNgo(ngoId);
	}

	@PostMapping("/volapps/{id}/status")
	public ResponseDto changeStatus(@PathVariable("id") String id, @RequestParam String status) {
		return volAppService.updateStatus(id, status);
	}
}