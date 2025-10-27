package com.example.ngo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ngo.dtos.VolunteerApplicationDto;
import com.example.ngo.model.VolunteerApplication;
import com.example.ngo.util.ServicesUtil;

public interface VolunteerApplicationRepository extends JpaRepository<VolunteerApplication, String> {

	public default VolunteerApplication importDto(VolunteerApplicationDto dto) {
		VolunteerApplication entity = null;
		if (!ServicesUtil.isEmpty(dto)) {
			entity = new VolunteerApplication();
			BeanUtils.copyProperties(dto, entity);
		}
		return entity;
	}

	public default VolunteerApplicationDto exportDto(VolunteerApplication entity) {
		VolunteerApplicationDto dto = null;
		if (!ServicesUtil.isEmpty(entity)) {
			dto = new VolunteerApplicationDto();
			BeanUtils.copyProperties(entity, dto);
		}
		return dto;
	}

	public default List<VolunteerApplicationDto> exportDtoList(List<VolunteerApplication> entities) {

		List<VolunteerApplicationDto> dtoList = new ArrayList<>();

		for (VolunteerApplication entity : entities) {
			VolunteerApplicationDto dto = new VolunteerApplicationDto();
			if (!ServicesUtil.isEmpty(entity)) {
				BeanUtils.copyProperties(entity, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	List<VolunteerApplication> findByNgoId(String ngoId);
}