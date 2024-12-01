package com.first.pract.firstPractice.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.first.pract.firstPractice.entity.UserRegistration;
import com.first.pract.firstPractice.repo.UserRegistrationRepo;

import jakarta.persistence.GeneratedValue;

@Service
public class UserRegistrationInfoService implements UserDetailsService {
	
	@Autowired
	UserRegistrationRepo registrationRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserRegistration>ur= registrationRepo.findByEmail(username);
		if(ur.isEmpty())
			throw new UsernameNotFoundException("Invalid");
		return new User(ur.get().getEmail(), ur.get().getPassword(), getAuthority(ur.get().getRole()));
	}

	private Collection<? extends GrantedAuthority> getAuthority(String role) {
		List<GrantedAuthority>l=new ArrayList<>();
		l.add(new SimpleGrantedAuthority("ROLE_".concat(role)));
		return l;
	}

}
