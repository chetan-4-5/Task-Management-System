package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	List<User> findByRole(String role);
	User findByUsername(String username);
	void deleteById(Long employeeId);
	
}

