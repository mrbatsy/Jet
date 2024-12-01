package com.first.pract.firstPractice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.pract.firstPractice.entity.UserRegistration;
import com.first.pract.firstPractice.repo.UserRegistrationRepo;

@Service
public class UserRegistrationService {
	
	@Autowired
	private UserRegistrationRepo userRegistrationRepo;
	
	public List<UserRegistration> getAllApplicants() {
		return userRegistrationRepo.findAll();		
	}

	public Optional<UserRegistration> getApplicantById(int id) {
		return userRegistrationRepo.findById(id);
		
	}

	public void saveApplicant(UserRegistration user) {
		userRegistrationRepo.save(user);
		
	}

	public UserRegistration updateApplicant(int id, UserRegistration user) {
		Optional<UserRegistration> byId = userRegistrationRepo.findById(id);
		if(byId.isEmpty()) {
			return null;
		}
		else
			return byId.get();
	}

	public void deleteApplicant(int id) {
		userRegistrationRepo.deleteById(id);
		
	}

}
