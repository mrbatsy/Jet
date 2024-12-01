package com.first.pract.firstPractice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.pract.firstPractice.entity.UserRegistration;

@Repository
public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Integer>{

	Optional<UserRegistration> findByEmail(String username);

}
