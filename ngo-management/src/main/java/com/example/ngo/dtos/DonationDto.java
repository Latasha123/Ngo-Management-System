package com.example.ngo.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DonationDto {

	private String id;
	private String donorId;
	private String ngoId;
	private Double amount;
	private String item;
	private LocalDateTime donatedAt;
	private boolean approved;
}
