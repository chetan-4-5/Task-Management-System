package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.User;
import com.klu.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	@Override
	public List<User> findUsersByRole(String role) {
	    return userRepository.findByRole(role);
	}
	
	@Override
    public User findById(Long userId) {
        
        return userRepository.findById(userId).orElse(null); 
    }
	
	@Override
	public void deleteEmployee(Long taskId) {
		userRepository.deleteById(taskId);
	}


	
}
