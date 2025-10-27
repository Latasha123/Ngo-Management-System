package com.example.ngo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.dtos.VolunteerApplicationDto;
import com.example.ngo.model.User;
import com.example.ngo.model.VolunteerApplication;
import com.example.ngo.repository.UserRepository;
import com.example.ngo.repository.VolunteerApplicationRepository;

@Service
public class VolunteerApplicationService {
	private final VolunteerApplicationRepository volunteerRepo;

	@Autowired
	private UserRepository userRepo;

	public VolunteerApplicationService(VolunteerApplicationRepository repo) {
		this.volunteerRepo = repo;
	}

	public ResponseDto apply(String ngoId, VolunteerApplicationDto v, UserDetails ud) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			User u = userRepo.findByUsername(ud.getUsername()).orElseThrow();
			v.setDonorId(u.getId());
			v.setNgoId(ngoId);
			v.setStatus("PENDING");

			VolunteerApplication volunteerAppDo = volunteerRepo.save(volunteerRepo.importDto(v));
			responseDto.setData(volunteerAppDo.getId());
			responseDto.setMessage("Volunteer Application submitted successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto byNgo(String ngoId) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			List<VolunteerApplicationDto> volunteerDetails = volunteerRepo
					.exportDtoList(volunteerRepo.findByNgoId(ngoId));
			responseDto.setData(volunteerDetails);
			responseDto.setMessage("Volunteer Application submitted successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto updateStatus(String id, String status) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			Optional<VolunteerApplication> volunteerDetails = volunteerRepo.findById(id);
			if (volunteerDetails.isEmpty()) {
				responseDto.setMessage("Volunteer Not Found");
			} else {
				volunteerDetails.get().setStatus(status);
				VolunteerApplicationDto volunteerDto = volunteerRepo
						.exportDto(volunteerRepo.save(volunteerDetails.get()));
				responseDto.setData(volunteerDto);
				responseDto.setMessage("Volunteer Application status changed");
			}
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}
}