package com.example.ngo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ngo.dtos.ResponseDto;
import com.example.ngo.dtos.UserDto;
import com.example.ngo.model.Role;
import com.example.ngo.model.User;
import com.example.ngo.repository.RoleRepository;
import com.example.ngo.repository.UserRepository;
import com.example.ngo.util.JwtUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder encoder;
//	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
	}

	@Autowired
	private JwtUtils jwtUtils;

	public ResponseDto registerUser(UserDto user) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			if (user.getFullName() == null || user.getPassword() == null) {
				responseDto.setMessage("username and password required");
				return responseDto;
			}
			// Check if username is unique
			if (userRepository.existsByUsername(user.getUsername())) {
				responseDto.setMessage("Username already exists");
				return responseDto;
			}

			// Check if email is unique
			if (userRepository.existsByEmail(user.getEmail())) {
				responseDto.setMessage("Email already exists");
				return responseDto;
			}
			user.setPassword(encoder.encode(user.getPassword()));
			Optional<Role> r = roleRepository.findByRoleName("DONOR");
			if (r.isEmpty()) {
				Role donorRole = new Role(null, "DONOR");
				donorRole = roleRepository.save(donorRole);
				user.setRoleId(donorRole.getId());
			} else {
				user.setRoleId(r.get().getId());
			}

			User userDetails = userRepository.save(userRepository.importDto(user));
			responseDto.setData(userDetails);
			responseDto.setMessage("User Registerd successfully");
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findAllUsers() {
		List<User> users = userRepository.findAll();
		System.out.println(users);
		return users;
	}

	public ResponseDto doLogin(UserDto user) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(200);
		responseDto.setStatus(true);
		try {
			if (user.getEmail() == null || user.getPassword() == null) {
				responseDto.setMessage("email and password required");
				return responseDto;
			}
			User userDetails = userRepository.getUserDetailsByEmailId(user.getEmail());

			if (userDetails != null) {
				if (!encoder.matches(user.getPassword(), userDetails.getPassword())) {
					responseDto.setMessage("Invalid credentials");
				} else {
					Role user_role = roleRepository.getById(userDetails.getRoleId());
					String token = jwtUtils.generateToken(user.getEmail(), user_role.getRoleName());
					responseDto.setMessage("Login SuccessFull");
					responseDto.setData(token);
				}
			} else {
				responseDto.setMessage("EmailId or password is incorrect");
			}
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		return responseDto;
	}
}