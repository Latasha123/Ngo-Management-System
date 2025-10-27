package com.example.ngo.repository;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ngo.dtos.UserDto;
import com.example.ngo.model.User;
import com.example.ngo.util.ServicesUtil;

public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);

	@Query("select d from User d where d.id =:donorId")
	User getByDonorId(@Param("donorId") String donorId);

	public default User importDto(UserDto userDto) {
		User entity = null;
		if (!ServicesUtil.isEmpty(userDto)) {
			entity = new User();
			BeanUtils.copyProperties(userDto, entity);
		}
		return entity;
	}

	@Query("select d from User d where d.email =:emailId")
	User getUserDetailsByEmailId(@Param("emailId") String emailId);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}