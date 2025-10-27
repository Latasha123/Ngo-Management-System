package com.example.ngo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ngo.dtos.NgoDto;
import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.model.NGO;
import com.example.ngo.repository.NGORepository;

@Service
public class NGOService {
	private final NGORepository ngoRepository;

	public NGOService(NGORepository ngoRepository) {
		this.ngoRepository = ngoRepository;
	}

	public ResponseDto getAllNGos() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			List<NgoDto> ngoDtos = ngoRepository.exportDtoList(ngoRepository.findAll());
			responseDto.setData(ngoDtos);
			responseDto.setMessage("NGO details fetched successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto getNGOById(String id) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			Optional<NGO> ngoDetails = ngoRepository.findById(id);
			responseDto.setData(ngoDetails);
			responseDto.setMessage("NGO details fetched successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public ResponseDto createNGO(NgoDto ngoDto) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {

			NGO ngoDo = ngoRepository.save(ngoRepository.importDto(ngoDto));
			ngoDto.setId(ngoDo.getId());
			responseDto.setData(ngoDto.getId());
			responseDto.setMessage("NGO created successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;

	}

	public ResponseDto updateNGODetails(String id, NgoDto ngoDto) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			NGO ngoDo = ngoRepository.save(ngoRepository.importDto(ngoDto));
			ngoDto.setId(ngoDo.getId());
			responseDto.setData(ngoDto.getId());
			responseDto.setMessage("NGO deatils updated successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;

	}

	public ResponseDto deleteNGO(String id) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			ngoRepository.deleteById(id);
			responseDto.setMessage("NGO deleted successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;

	}
}