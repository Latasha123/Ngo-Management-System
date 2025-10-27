package com.example.ngo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DONATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
	@Id
	@GeneratedValue
	@UuidGenerator
	private String id;
	private String donorId;
	private String ngoId;
	private Double amount;
	private String item;
	private LocalDateTime donatedAt;
	private Boolean approved;
}