package com.davidphan.springsecurity;

import com.davidphan.springsecurity.entities.Role;
import com.davidphan.springsecurity.entities.User;
import com.davidphan.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringsecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}
	public void run (String... args){
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAccount){
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setRole(Role.ADMIN);
			user.setFirstname("admin");
			user.setSecondname("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
