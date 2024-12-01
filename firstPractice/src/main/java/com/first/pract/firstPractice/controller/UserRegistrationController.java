package com.first.pract.firstPractice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.first.pract.firstPractice.entity.UserRegistration;
import com.first.pract.firstPractice.security.AuthReq;
import com.first.pract.firstPractice.security.JwtAuthService;
import com.first.pract.firstPractice.service.UserRegistrationService;

@RestController
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtAuthService jwtService;
	
	 @GetMapping("/admin/getAll")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public ResponseEntity < List < UserRegistration >> getAllApplicants() {
		 List<UserRegistration> allApplicant = userRegistrationService.getAllApplicants();
		 return new ResponseEntity<>(allApplicant,HttpStatus.OK);
	    }

	    @GetMapping("/users/{id}")
		 @PreAuthorize("hasAuthority('ROLE_USER')")
	    public ResponseEntity < UserRegistration > getUserById(@PathVariable int id) {
	    	Optional<UserRegistration> applicantById = userRegistrationService.getApplicantById(id);
	    	return new ResponseEntity<>(applicantById.get(),HttpStatus.ACCEPTED);
	    }

	    @PostMapping("/register")
	    public ResponseEntity createUser(@RequestBody UserRegistration applicant) {
	    	applicant.setPassword(applicant.getPassword());
	    	userRegistrationService.saveApplicant(applicant);
	        return ResponseEntity.ok().build();
	    }

	    @PutMapping("/users/{id}")
		 @PreAuthorize("hasAuthority('ROLE_USER')")
	    public ResponseEntity < UserRegistration > updateUser(@PathVariable int id, @RequestBody UserRegistration student) {
	    	UserRegistration updateApplicant = userRegistrationService.updateApplicant(id,student);
	    	return new ResponseEntity<>(updateApplicant,HttpStatus.OK);
	    }

	    @DeleteMapping("/users/{id}")
		 @PreAuthorize("hasAuthority('ROLE_USER')")
	    public HttpStatus deleteApplicant(@PathVariable int id) {
	    	userRegistrationService.deleteApplicant(id);
	        return HttpStatus.OK;
	    }
	    
	    @PostMapping("/generateToken")
	    public String generateToken(@RequestBody AuthReq authReq) {
	    	Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
	    			(authReq.getEmail(),authReq.getPassword()));
	    	if(!authentication.isAuthenticated()) {
	    		throw new UsernameNotFoundException("Invalid User");
	    	}
	    	return jwtService.generateToken(authReq.getEmail());
	    }

}
