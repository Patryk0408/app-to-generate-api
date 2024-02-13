package com.krawczyk.APItest;

import com.krawczyk.APItest.models.*;
import com.krawczyk.APItest.repository.CarRepository;
import com.krawczyk.APItest.repository.MarkRepository;
import com.krawczyk.APItest.repository.RoleRepository;
import com.krawczyk.APItest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	CommandLineRunner SetupTestUsers(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, PasswordEncoder encoder) {
		return args -> {
			Role userRole = new Role(ERole.ROLE_USER);
			Role adminRole = new Role(ERole.ROLE_ADMIN);
			roleRepository.save(userRole);
			roleRepository.save(adminRole);

			userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));

			User newUser = new User("User1", encoder.encode("Test"), "user@gmail.com", userRole);
			userRepository.save(newUser);

			User user2 = new User("User2", encoder.encode("Test"), "user2@gmail.com", adminRole);
			user2.addRole(userRole);
			userRepository.save(user2);
		};
	}

	@Bean
	CommandLineRunner SetupTestCars(CarRepository carRepository, UserRepository userRepository)
	{
		return args -> {
			var user = userRepository.getReferenceById(1L);
			var user2 = userRepository.getReferenceById(2L);
			Car newCar = new Car(user, "Nissan", "Tico", 1999, 290231);
			carRepository.save(newCar);
			Car newCar2 = new Car(user2, "BMW", "PASSAT", 2007, 102065);
			carRepository.save(newCar2);
		};
	}

	@Bean
	CommandLineRunner SetupTestMarks(MarkRepository markRepository, UserRepository userRepository)
	{
		return args -> {
			var user = userRepository.getReferenceById(1L);
			var user2 = userRepository.getReferenceById(2L);
			Mark newMark = new Mark(user, "Programowanie w UML", 4);
			markRepository.save(newMark);
			Mark newMark2 = new Mark(user2, "Zaawansowane bazy danych", 5);
			markRepository.save(newMark2);
		};
	}
}
