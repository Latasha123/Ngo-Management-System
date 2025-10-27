package com.example.ngo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.dtos.VolunteerApplicationDto;
import com.example.ngo.repository.UserRepository;
import com.example.ngo.service.FundraiserService;
import com.example.ngo.service.VolunteerApplicationService;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerApplicationController {
	private final FundraiserService frService;
	private final VolunteerApplicationService volAppService;

	public VolunteerApplicationController(FundraiserService frService, VolunteerApplicationService volAppService,
			UserRepository userRepo) {
		this.frService = frService;
		this.volAppService = volAppService;
	}

	@GetMapping("/fundraisers")
	public ResponseDto getAllFundraisers() {
		return frService.getAllFundraisers();
	}

	@PostMapping("/apply/{ngoId}")
	public ResponseDto apply(@PathVariable("ngoId") String ngoId, @RequestBody VolunteerApplicationDto app,
			@AuthenticationPrincipal UserDetails ud) {
		return volAppService.apply(ngoId, app, ud);
	}
}