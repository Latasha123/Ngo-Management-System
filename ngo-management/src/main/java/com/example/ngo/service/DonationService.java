package com.example.ngo.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ngo.dtos.DonationDto;
import com.example.ngo.dtos.EmailDto;
import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.model.Donation;
import com.example.ngo.model.NGO;
import com.example.ngo.model.User;
import com.example.ngo.repository.DonationRepository;
import com.example.ngo.repository.NGORepository;
import com.example.ngo.repository.UserRepository;
import com.example.ngo.util.EmailService;

@Service
public class DonationService {
	private final DonationRepository donationRepo;
	private final EmailService emailService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private NGORepository ngoRepo;

	public DonationService(DonationRepository repo, EmailService emailService) {
		this.donationRepo = repo;
		this.emailService = emailService;
	}

	public ResponseDto donate(DonationDto donationDto) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			if (donationDto.getAmount().equals("0.0") && donationDto.getItem() == null) {
				responseDto.setMessage("Either Amount or items is required");
				responseDto.setStatus(false);
				return responseDto;
			}
			Donation donationDo = donationRepo.save(donationRepo.importDto(donationDto));
			donationDto.setId(donationDo.getId());
			// getUserDetails
			User userDetails = userRepo.getByDonorId(donationDto.getDonorId());
			NGO ngoDetails = ngoRepo.getByNgoId(donationDto.getNgoId());

			// send mail notification to donor
			EmailDto emailDto = new EmailDto();
			emailDto.setName(ngoDetails.getName());
			emailDto.setSubject("Thank You for Your Donation!");
			emailDto.setContent("Dear " + userDetails.getFullName() + ",\n\n"
					+ "We truly appreciate your generous donation of ₹" + donationDto.getAmount() + " to "
					+ ngoDetails.getName() + ". Your support makes a real difference!\n\n" + "With gratitude,\n"
					+ ngoDetails.getName() + " Team");
			emailDto.setCreatedOn(new Date());
			emailDto.setToList(Arrays.asList(userDetails.getEmail()));
			emailDto.setTo(userDetails.getEmail());
			emailDto.setAttachments("C:\\Users\\latasha.rathod\\Documents\\Folder/Thankyou_Image.jpg");
			emailService.sendMail(emailDto);
			responseDto.setData(donationDo.getId());
			responseDto.setMessage("Donation is complated successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto byDonor(String donorId) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			List<DonationDto> donationDtos = donationRepo.exportDtoList(donationRepo.findByDonorId(donorId));
			responseDto.setMessage("Donation details fetched successfully");
			responseDto.setData(donationDtos);
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto allDonations() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			List<DonationDto> donationDtos = donationRepo.exportDtoList(donationRepo.findAll());
			responseDto.setMessage("Donation details fetched successfully");
			responseDto.setData(donationDtos);
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto approve(String id, Boolean approved) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			Donation d = donationRepo.findById(id).orElseThrow();
			d.setApproved(approved);
			d = donationRepo.save(d);
			responseDto.setMessage("Donation approved successfully");
			responseDto.setData(d);
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto getdonationsByNgoId(String ngoId) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			List<DonationDto> donationDtos = donationRepo.exportDtoList(donationRepo.findByNgoId(ngoId));
			responseDto.setMessage("Donation details fetched successfully");
			responseDto.setData(donationDtos);
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}
}