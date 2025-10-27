package com.example.ngo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.ngo.dtos.UserDto;
import com.example.ngo.model.User;
import com.example.ngo.repository.UserRepository;
import com.example.ngo.service.UserService;

@SpringBootTest
public class NgoManagementApplicationTest {

	@Autowired
	private UserService userService;

	@MockitoBean
	private UserRepository userRepo;

	@Test
	public void findAllUsersTest() {
		when(userRepo.findAll()).thenReturn(
				Stream.of(new User("101", "user1", "password", "userFull", "email", 97487392L, "bangluru", null))
						.collect(Collectors.toList()));

		assertEquals(1, userService.findAllUsers().size());
	}

	@Test
	public void findByUsernameTest() {
		when(userRepo.findByUsername("user1")).thenReturn(
				Optional.of(new User("101", "user1", "password", "userFull", "email", 97487392L, "bangluru", null)));

		assertEquals("user1", userService.findByUsername("user1").get().getUsername());
//		assertEquals(1, userService.findByUsername("user1").empty());
	}

	@Test
	public void registerUserTest() {
		UserDto userDto = new UserDto("101", "user1", "password", "userFull", "email", 97487392L, "bangluru", null);
		User user = userRepo.importDto(userDto);
		when(userRepo.save(user)).thenReturn(user);

		assertEquals(200, userService.registerUser(userDto).getStatusCode());
	}

}
