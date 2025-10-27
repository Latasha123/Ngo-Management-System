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
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue
	@UuidGenerator
	private String id;
	@Column(unique = true, nullable = false)
	private String username;
	private String password;
	private String fullName;
	@Column(unique = true, nullable = false)
	private String email;
	private Long phoneNumber;
	@Column(length = 1000)
	private String address;
	private String roleId;
}