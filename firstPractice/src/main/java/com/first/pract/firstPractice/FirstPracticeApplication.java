package com.first.pract.firstPractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.first.pract.firstPractice.entity.UserRegistration;
import com.first.pract.firstPractice.service.UserRegistrationService;

@SpringBootApplication
public class FirstPracticeApplication implements CommandLineRunner{

	@Autowired
	private UserRegistrationService userRegistrationService;
	
	
	public static void main(String[] args)  {
		SpringApplication.run(FirstPracticeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		UserRegistration [] userRegistrations=new UserRegistration[] {
				new UserRegistration("k@gmail.com","123456","ADMIN"),
				new UserRegistration("r@gmail.com","654321","USER")
		};
		
		for(int i=0;i<userRegistrations.length;i++) {
			userRegistrationService.saveApplicant(userRegistrations[i]);
		}
		
	}

}
