package com.example.ngo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ngo.dtos.DonationDto;
import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.dtos.UserDto;
import com.example.ngo.service.DonationService;
import com.example.ngo.service.NGOService;
import com.example.ngo.service.UserService;

@Controller
public class HomeController {

	private final NGOService ngoService;
	private final DonationService donationService;
	private final UserService userService;

	public HomeController(NGOService ngoService, DonationService donationService, UserService userService) {
		this.ngoService = ngoService;
		this.donationService = donationService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String homePage() {
		return "index"; // resolves to templates/index.html
	}

	@GetMapping("/ngos")
	public String showNgos(Model model) {
		model.addAttribute("ngos", ngoService.getAllNGos().getData());
		// assuming ResponseDto.getData() returns list of NGOs
		return "ngos"; // templates/ngos.html
	}

	// Show donation form
	@GetMapping("/donate")
	public String showDonationForm(Model model) {
		model.addAttribute("donationRequest", new DonationDto());
		return "donation"; // Thymeleaf template donation.html
	}

	// Handle donation submission
	@PostMapping("/donate")
	public String donate(@ModelAttribute DonationDto donationRequest, Model model) {
		ResponseDto response = donationService.donate(donationRequest);
		model.addAttribute("response", response);
		return "donation_success"; // show confirmation page
	}

	// Show login form
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("loginRequest", new UserDto());
		return "login"; // thymeleaf page
	}

	@PostMapping("/login")
	@ResponseBody
	public String login(UserDto loginRequest, Model model) {
		ResponseDto response = userService.doLogin(loginRequest);
		return response.getMessage();
	}

	// Show login form
	@GetMapping("/registrationFrom")
	public String showRegistrationForm(Model model) {
		model.addAttribute("registerRequest", new UserDto());
		return "register"; // thymeleaf page
	}

	// Handle login
	@PostMapping("/register")
	@ResponseBody
	public String register(@ModelAttribute UserDto loginRequest, Model model) {
		ResponseDto response = userService.registerUser(loginRequest);

		if (response.isStatus() && response.getStatusCode() == 200) {
			return response.getMessage();
		} else {
			model.addAttribute("error", response.getMessage());
			return "register"; // reload login page with error
		}
	}
}
