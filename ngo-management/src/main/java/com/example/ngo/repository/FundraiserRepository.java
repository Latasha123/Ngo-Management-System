package com.example.ngo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ngo.dtos.FundraiserDto;
import com.example.ngo.model.Fundraiser;
import com.example.ngo.util.ServicesUtil;

public interface FundraiserRepository extends JpaRepository<Fundraiser, String> {

	List<Fundraiser> findByNgoId(String ngoId);

	public default List<FundraiserDto> exportDtoList(List<Fundraiser> entities) {

		List<FundraiserDto> dtoList = new ArrayList<>();

		for (Fundraiser entity : entities) {
			FundraiserDto dto = new FundraiserDto();
			if (!ServicesUtil.isEmpty(entity)) {
				BeanUtils.copyProperties(entity, dto);
				dtoList.add(dto);
			}
		}

		return dtoList;
	}

	public default Fundraiser importDto(FundraiserDto dto) {
		Fundraiser entity = null;
		if (!ServicesUtil.isEmpty(dto)) {
			entity = new Fundraiser();
			BeanUtils.copyProperties(dto, entity);
		}
		return entity;
	}
}