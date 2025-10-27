package com.example.ngo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ngo.dtos.FundraiserDto;
import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.model.Fundraiser;
import com.example.ngo.repository.FundraiserRepository;

@Service
public class FundraiserService {
	private final FundraiserRepository repo;

	public FundraiserService(FundraiserRepository repo) {
		this.repo = repo;
	}

	public ResponseDto create(FundraiserDto frDto) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			Fundraiser FundraiserDo = repo.save(repo.importDto(frDto));
			frDto.setId(FundraiserDo.getId());
			responseDto.setData(frDto);
			responseDto.setMessage("NGO details fetched successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto findByNgo(String ngoId) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			List<FundraiserDto> FundraiserDtos = repo.exportDtoList(repo.findByNgoId(ngoId));
			responseDto.setData(FundraiserDtos);
			responseDto.setMessage("NGO details fetched successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto getAllFundraisers() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			List<FundraiserDto> FundraiserDtos = repo.exportDtoList(repo.findAll());
			responseDto.setData(FundraiserDtos);
			responseDto.setMessage("NGO details fetched successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}
}