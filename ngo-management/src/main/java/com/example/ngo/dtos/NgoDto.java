package com.example.ngo.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NgoDto {

	private String id;
	private String name;
	private String description;
	private String issuesAddressed;
	private String fcraNumber;
	private String address;
	private Long phoneNumber;
	private String email;
	private String website;
	private LocalDate dateOfRegistration;
}
