package com.example.ngo.model;

import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NGOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NGO {
	@Id
	@GeneratedValue
	@UuidGenerator
	private String id;

	private String name;

	@Column(length = 1000)
	private String description;

	@Column(length = 2000)
	private String issuesAddressed;

	private String fcraNumber;

	@Column(length = 2000)
	private String address;

	private Long phoneNumber;

	private String email;

	private String website;

	private LocalDate dateOfRegistration;
}