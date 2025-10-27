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
@Table(name = "VOL_APPLICATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerApplication {
	@Id
	@GeneratedValue
	@UuidGenerator
	private String id;
	private String donorId;
	private String ngoId;
	@Column(length = 1000)
	private String note;
	private String status;
}