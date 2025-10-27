package com.example.ngo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ngo.dtos.DonationDto;
import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.model.User;
import com.example.ngo.repository.FundraiserRepository;
import com.example.ngo.repository.NGORepository;
import com.example.ngo.repository.UserRepository;
import com.example.ngo.service.DonationService;

@RestController
@RequestMapping("/api/donor")
public class DonorController {
	private final DonationService donationService;
	private final UserRepository userRepo;
//	private final SendGridEmailService emailService;

	public DonorController(DonationService donationService, NGORepository ngoRepo, FundraiserRepository frRepo,
			UserRepository userRepo) {
		this.donationService = donationService;
		this.userRepo = userRepo;
//		this.emailService = emailService;
	}

	@PostMapping("/donate")
	public ResponseDto donate(@RequestBody DonationDto donationDto) {
		return donationService.donate(donationDto);
	}

	@GetMapping("/myDonations")
	public ResponseDto myDonations(@AuthenticationPrincipal UserDetails ud) {
		User donor = userRepo.findByUsername(ud.getUsername()).orElseThrow();
		return donationService.byDonor(donor.getId());
	}

	@GetMapping("/donationsByNgoId")
	public ResponseDto getdonationsByNgoId(@RequestParam("ngoId") String ngoId) {
		return donationService.getdonationsByNgoId(ngoId);
	}

//	@PostMapping("/send-plain")
//	public ResponseEntity<?> sendPlain(@RequestBody Map<String, String> body) throws IOException {
//		String from = body.get("from"); // e.g. "testngo@example.com"
//		String to = body.get("to");
//		String subject = body.get("subject");
//		String text = body.get("text");
//
//		Response resp = emailService.sendPlainTextEmail(from, to, subject, text);
//		return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
//	}
}