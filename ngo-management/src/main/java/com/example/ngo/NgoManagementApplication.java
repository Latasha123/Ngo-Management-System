package com.example.ngo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ngo.model.Fundraiser;
import com.example.ngo.model.NGO;
import com.example.ngo.model.Role;
import com.example.ngo.model.User;
import com.example.ngo.repository.FundraiserRepository;
import com.example.ngo.repository.NGORepository;
import com.example.ngo.repository.RoleRepository;
import com.example.ngo.repository.UserRepository;

@SpringBootApplication
public class NgoManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgoManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, NGORepository ngoRepo,
			FundraiserRepository frRepo, BCryptPasswordEncoder encoder) {
		return args -> {
			if (roleRepo.count() == 0) {
				Role admin = new Role(null, "ADMIN");
				Role volunteer = new Role(null, "VOLUNTEER");
				Role donor = new Role(null, "DONOR");
				roleRepo.save(admin);
				roleRepo.save(volunteer);
				roleRepo.save(donor);
				User a = new User(null, "admin", encoder.encode("adminpass"), "Admin User", "latasha0425@gmail.com",
						9340892349L, "Bangluru", admin.getId());
				userRepo.save(a);
//				User v = new User(null, "vol1", encoder.encode("volpass"), "Volunteer One", "vol1@ngo.com",
//						volunteer.getId());
//				userRepo.save(v);
//				User d = new User(null, "donor", encoder.encode("donorpass"), "Donor One", "donor@ngo.com",
//						donor.getId());
//				userRepo.save(d);
				NGO ngo = new NGO(null, "Shubhodaya Charitable Trust", "Support community education",
						"Agriculture,Art & Culture,Children Education & Literacy ,Rural Development", "NA",
						"No.544/14, 7th Cross, 1st Main Road, Gokula 1st Stage, 2nd Phase, Yeshwanthpur, Bangalore-560022",
						9916265003L, null, "shubhodayatrust.org", LocalDate.of(2013, 10, 9));
				ngo = ngoRepo.save(ngo);
				frRepo.save(new Fundraiser(null, "Books for Kids", "Collecting books", ngo.getId(), true));
			}
		};
	}
}
