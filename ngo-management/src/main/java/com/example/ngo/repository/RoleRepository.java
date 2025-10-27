package com.example.ngo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ngo.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	Optional<Role> findByRoleName(String name);
}