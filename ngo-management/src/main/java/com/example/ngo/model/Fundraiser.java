package com.example.ngo.model;

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
@Table(name = "FUND_RAISERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fundraiser {
	@Id
	@GeneratedValue
	@UuidGenerator
	private String id;
	private String title;
	@Column(length = 1500)
	private String description;
	private String ngoId;
	private boolean active;
}