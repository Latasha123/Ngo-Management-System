package com.example.ngo.dtos;

import lombok.Data;

@Data
public class FundraiserDto {

	private String id;
	private String title;
	private String description;
	private String ngoId;
	private boolean active;
}
