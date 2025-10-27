package com.example.ngo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ngo.dtos.DonationDto;
import com.example.ngo.model.Donation;
import com.example.ngo.util.ServicesUtil;

public interface DonationRepository extends JpaRepository<Donation, String> {

	public default Donation importDto(DonationDto dto) {
		Donation entity = null;
		if (!ServicesUtil.isEmpty(dto)) {
			entity = new Donation();
			BeanUtils.copyProperties(dto, entity);
		}
		return entity;
	}

	public default DonationDto exportDto(Donation entity) {
		DonationDto dto = null;
		if (!ServicesUtil.isEmpty(entity)) {
			dto = new DonationDto();
			BeanUtils.copyProperties(entity, dto);
		}
		return dto;
	}

	public default List<DonationDto> exportDtoList(List<Donation> entities) {

		List<DonationDto> dtoList = new ArrayList<>();

		for (Donation entity : entities) {
			DonationDto dto = new DonationDto();
			if (!ServicesUtil.isEmpty(entity)) {
				BeanUtils.copyProperties(entity, dto);
				dtoList.add(dto);
			}
		}

		return dtoList;
	}

	List<Donation> findByNgoId(String ngoId);

	List<Donation> findByDonorId(String donorId);
}