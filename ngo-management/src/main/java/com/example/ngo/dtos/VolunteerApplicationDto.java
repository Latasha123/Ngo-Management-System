package com.example.ngo.dtos;

import lombok.Data;

@Data
public class VolunteerApplicationDto {

	private String id;
	private String donorId;
	private String ngoId;
	private String note;
	private String status;
}
