package com.example.ngo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.ngo.model.Role;
import com.example.ngo.model.User;
import com.example.ngo.repository.RoleRepository;
import com.example.ngo.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public CustomUserDetailsService() {
		this.userRepository = com.example.ngo.ApplicationContextProvider.getBean(UserRepository.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		Role role = roleRepository.findById(user.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));

		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword()).authorities(new SimpleGrantedAuthority(role.getRoleName())).build();
	}
}