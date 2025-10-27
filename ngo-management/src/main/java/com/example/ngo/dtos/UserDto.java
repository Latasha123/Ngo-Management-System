package com.example.ngo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String id;
	private String username;
	private String password;
	private String fullName;
	private String email;
	private Long phoneNumber;
	private String address;
	private String roleId;

}
