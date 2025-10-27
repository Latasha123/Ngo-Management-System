package com.example.ngo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ngo.dtos.NgoDto;
import com.example.ngo.model.NGO;
import com.example.ngo.util.ServicesUtil;

public interface NGORepository extends JpaRepository<NGO, String> {

	public default NGO importDto(NgoDto dto) {
		NGO entity = null;
		if (!ServicesUtil.isEmpty(dto)) {
			entity = new NGO();
			BeanUtils.copyProperties(dto, entity);
		}
		return entity;
	}

	public default NgoDto exportDto(NGO entity) {
		NgoDto dto = null;
		if (!ServicesUtil.isEmpty(entity)) {
			dto = new NgoDto();
			BeanUtils.copyProperties(entity, dto);
		}
		return dto;
	}

	public default List<NgoDto> exportDtoList(List<NGO> entities) {

		List<NgoDto> dtoList = new ArrayList<>();

		for (NGO entity : entities) {
			NgoDto dto = new NgoDto();
			if (!ServicesUtil.isEmpty(entity)) {
				BeanUtils.copyProperties(entity, dto);
				dtoList.add(dto);
			}
		}

		return dtoList;
	}

	@Query("select ng from NGO ng where ng.id =:ngoId")
	public NGO getByNgoId(@Param("ngoId") String ngoId);
}